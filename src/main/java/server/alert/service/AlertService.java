package server.alert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;
import server.alert.model.*;
import server.alert.repository.AlertRepository;
import server.threshold.model.AlertThreshold;
import server.threshold.service.ThresholdService;
import server.vehicleData.model.VehicleData;
import server.vehicleData.service.VehicleDataService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class AlertService {
    @Autowired
    private ThresholdService thresholdService;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private VehicleDataService vehicleDataService;
    public void checkThreshold(VehicleData vehicleData) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        List<AlertThreshold> alertThresholdList = thresholdService
                .getAlertThresholdsByVehicleId(vehicleData.getVehicleId());
        alertThresholdList.forEach(alertThreshold -> {
            if(thresholdService.evaluate(alertThreshold, vehicleData)){
                if( alertThreshold.getAlertType().equals(AlertType.LowCharge)){
                    generateLowChargeAlert(alertThreshold, vehicleData);
                }
                else{
                    generateAlert(alertThreshold, vehicleData);
                }

            }
        });



    }

    private void generateLowChargeAlert(AlertThreshold alertThreshold, VehicleData vehicleData) {
        Double min_cell_voltage = vehicleDataService.getMinBatteryVoltageLastHour(vehicleData.getVehicleId());
        LowChargeFields lowChargeFields = new LowChargeFields(min_cell_voltage, vehicleData.getLastSeenAt(), 0);
        Alert alert = new Alert(UUID.randomUUID().toString(),
                alertThreshold.getAlertType().toString(), vehicleData.getVehicleId(), "Low Battery",
                vehicleData.getBatteryPercentage().toString(), "Unresolved", LocalDateTime.now(),
                LocalDateTime.now(),lowChargeFields , vehicleData.getLatitude(), vehicleData.getLatitudeDirection(),
                vehicleData.getLongitude(), vehicleData.getLatitudeDirection(), alertThreshold.getSeverity(),
                null, LocalDateTime.now(), null, alertThreshold.getId());
        alertRepository.save(alert);
    }

    private void generateAlert(AlertThreshold alertThreshold, VehicleData vehicleData) {
        Alert alert = new Alert(UUID.randomUUID().toString(),
                alertThreshold.getAlertType().toString(), vehicleData.getVehicleId(), "Low Battery",
                vehicleData.getBatteryPercentage().toString(), "Unresolved", LocalDateTime.now(),
                LocalDateTime.now(),null , vehicleData.getLatitude(), vehicleData.getLatitudeDirection(),
                vehicleData.getLongitude(), vehicleData.getLatitudeDirection(), alertThreshold.getSeverity(),
                null, LocalDateTime.now(), null, alertThreshold.getId());
        alertRepository.save(alert);
    }

    public Map<String, Double> getCriticalAlertPercentageByAlertType() {
        // Define the aggregation pipeline
        Aggregation aggregation = newAggregation(
                group("alertType")
                        .count().as("total")
                        .sum(conditional(Criteria.where("severity").is("Critical"), 1, 0)).as("criticalCount"),
                project("_id")
                        .and("total").as("total")
                        .and("criticalCount").as("criticalCount")
                        .andExpression("criticalCount / total * 100").as("percentage")
        );

        // Execute the aggregation
        AggregationResults<AlertPercentage> results = mongoTemplate.aggregate(aggregation, "alert", AlertPercentage.class);

        // Prepare the result map with default values
        Map<String, Double> result = new HashMap<>();

        // Populate the map with the results
        for (AlertPercentage alertPercentage : results.getMappedResults()) {
            result.put(alertPercentage.getAlertType(), alertPercentage.getPercentage());
        }

        // Ensure all enum values are included, even if no data
        for (AlertType type : AlertType.values()) {
            result.putIfAbsent(type.name(), 0.0);
        }

        return result;
    }


    public List<Alert> getAllAlert(Integer pageSize, Integer pageNumber) {
        // Create Pageable object with pageNumber and pageSize
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Create the MongoDB query and apply pagination
        Query query = new Query().with(pageable);

        // Fetch the list of alerts using MongoTemplate with pagination
        return (List<Alert>) mongoTemplate.find(query, Alert.class);
    }
}

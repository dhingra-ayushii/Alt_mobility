package server.alert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
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
import java.util.stream.Collectors;

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
        // Step 1: Match only alerts with severity "Critical"
        AggregationOperation matchCritical = match(Criteria.where("severity").is("Critical"));

        // Step 2: Group by alertType and count the total occurrences per alertType
        AggregationOperation groupByAlertType = group("alertType")
                .count().as("totalCriticalAlerts");

        // Step 3: Calculate the percentage of each alertType
        AggregationOperation projectPercentage = project()
                .and(ConditionalOperators.Cond
                        .when(ComparisonOperators.Gt.valueOf("totalCriticalAlerts").greaterThanValue(0))
                        .thenValueOf("totalCriticalAlerts")
                        .otherwise(0)
                ).as("percentage");

        // Build the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                matchCritical,
                groupByAlertType,
                projectPercentage
        );

        // Execute the aggregation and map the results
        AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "alert", Map.class);

        // Convert the result to a Map<String, Double> with alertType as key and percentage as value
        return result.getMappedResults().stream()
                .collect(Collectors.toMap(
                        entry -> entry.get("_id").toString(),
                        entry -> Double.valueOf(entry.get("percentage").toString())
                ));
    }
    public List<Alert> getAllAlert() {
        return alertRepository.findAll();
    }
}

package server.alert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.alert.model.AdditionalFields;
import server.alert.model.Alert;
import server.alert.model.AlertType;
import server.alert.model.LowChargeFields;
import server.alert.repository.AlertRepository;
import server.threshold.model.AlertThreshold;
import server.threshold.service.ThresholdService;
import server.vehicleData.model.VehicleData;
import server.vehicleData.service.VehicleDataService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AlertService {
    @Autowired
    private ThresholdService thresholdService;

    @Autowired
    private AlertRepository alertRepository;

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
}

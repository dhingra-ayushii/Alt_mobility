package server.threshold.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.threshold.model.AlertThreshold;
import server.threshold.model.ThresholdParameter;
import server.threshold.repository.ThresholdRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class ThresholdService {
    @Autowired
    private ThresholdRepository thresholdRepository;

    public List<ThresholdParameter> getParameters() {
        return Arrays.asList(ThresholdParameter.values());
    }

    public AlertThreshold saveAlertThreshold(AlertThreshold alertThreshold) {
        return thresholdRepository.save(alertThreshold);
    }

    public List<AlertThreshold> getAllAlertThresholds() {
        return thresholdRepository.findAll();
    }

    public List<AlertThreshold> getAlertThresholdsByVehicleId(String vehicleId) {
        return thresholdRepository.findByVehicleId(vehicleId);
    }

}

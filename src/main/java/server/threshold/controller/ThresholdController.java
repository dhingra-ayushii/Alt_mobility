package server.threshold.controller;


import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.threshold.model.AlertThreshold;
import server.threshold.model.ThresholdParameter;
import server.threshold.service.ThresholdService;


import java.util.List;

@RestController
@RequestMapping("/threshold")
@Timed
public class ThresholdController {
    @Autowired
    private ThresholdService thresholdService;
    @GetMapping("/parameters")
    public List<ThresholdParameter> getParameters() {
        return thresholdService.getParameters();
    }
    @PostMapping("/create")
    public AlertThreshold createAlertThreshold(@RequestBody AlertThreshold alertThreshold) {
        return thresholdService.saveAlertThreshold(alertThreshold);
    }

    @GetMapping("/getAll")
    public List<AlertThreshold> getAllAlertThresholds() {
        return thresholdService.getAllAlertThresholds();
    }

    @GetMapping("/getByVehicleId")
    public List<AlertThreshold> getAlertThresholdsByVehicleId(@RequestParam String vehicleId) {
        List<AlertThreshold> alertThresholds = thresholdService.getAlertThresholdsByVehicleId(vehicleId);
        if (alertThresholds.isEmpty()) {
            return null;
        }
        return alertThresholds;
    }
}

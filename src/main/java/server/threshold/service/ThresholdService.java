package server.threshold.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.threshold.model.AlertThreshold;
import server.threshold.model.Threshold;
import server.threshold.model.ThresholdParameter;
import server.threshold.repository.ThresholdRepository;
import server.vehicleData.model.VehicleData;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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

    public boolean evaluate(AlertThreshold alertThreshold, VehicleData vehicleData) {
            // Convert VehicleData to JSON
            String vehicleDataJson = new Gson().toJson(vehicleData);

            // Get the threshold parameter
            Threshold threshold = alertThreshold.getThresholds().get(0);
            String thresholdValue = threshold.getThresholdValue();
            String conditionSymbol = threshold.getCondition().name();

            // Parse JSON to access the parameter dynamically
            JsonObject jsonObject = new Gson().fromJson(vehicleDataJson, JsonObject.class);
            String parameterName = threshold.getThresholdParameter().toString(); // Assuming it matches the JSON key
            String vehicleValueString = jsonObject.get(parameterName).getAsString();

            String expression = vehicleValueString + " " + conditionSymbol + " " + thresholdValue;

            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("JavaScript");
            try {
                return (Boolean) engine.eval(expression);
            } catch (ScriptException e) {
                e.printStackTrace();
                return false;
        }
    }
}

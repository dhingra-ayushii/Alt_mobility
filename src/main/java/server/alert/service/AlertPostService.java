package server.alert.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import server.alert.model.AlertMessage;



@Service
public class AlertPostService {

    private Map<Long, AlertMessage> alertStore = new HashMap<>(); // In-memory store for alerts

    public AlertMessage updateAlert(long id, AlertMessage updatedAlert) {
        if (alertStore.containsKey(id)) {
            AlertMessage existingAlert = alertStore.get(id);
            existingAlert.setAlertType(updatedAlert.getAlertType());
            existingAlert.setVehicleId(updatedAlert.getVehicleId());
            existingAlert.setMessage(updatedAlert.getMessage());
            existingAlert.setValue(updatedAlert.getValue());
            existingAlert.setStatus(updatedAlert.getStatus());
            existingAlert.setUpdatedAt(updatedAlert.getUpdatedAt());
            return existingAlert;
        } else {
            throw new IllegalArgumentException("Alert with ID " + id + " not found.");
        }
    }

    public void addAlert(AlertMessage alert) {
        alertStore.put(alert.getId(), alert);
    }

    public AlertMessage getAlert(long id) {
        return alertStore.get(id);
    }
}

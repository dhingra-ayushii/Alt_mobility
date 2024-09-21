package server.alert.controller;

import io.micrometer.core.annotation.Timed;
import server.alert.*;
import server.alert.model.AlertMessage;
import server.alert.service.AlertPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.alert.model.Alert;
import server.alert.service.AlertService;
import server.threshold.model.AlertThreshold;

import java.util.List;

@RestController
@RequestMapping("/vehicleData")
@Timed
public class AlertController {

	@Autowired
    private AlertPostService alertService;

    // Endpoint for updating an alert
    @PostMapping("/update/{id}")
    public AlertMessage updateAlert(@PathVariable long id, @RequestBody AlertMessage alertMessage) {
        // Process the alert update
        AlertMessage updatedAlert = alertService.updateAlert(id, alertMessage);
        System.out.println("Updated Alert: " + updatedAlert);
        return updatedAlert;
    }
}

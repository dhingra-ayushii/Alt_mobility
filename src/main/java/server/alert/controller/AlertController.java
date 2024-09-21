package server.alert.controller;

import io.micrometer.core.annotation.Timed;
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

}

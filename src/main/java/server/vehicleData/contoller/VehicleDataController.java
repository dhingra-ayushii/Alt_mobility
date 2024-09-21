package server.vehicleData.contoller;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.vehicleData.request.VehicleDataRequest;
import server.vehicleData.service.VehicleDataService;

@RestController
@RequestMapping("/vehicleData")
@Timed
public class VehicleDataController {
    @Autowired
    private VehicleDataService vehicleDataService;

    @PostMapping("/report")
    public void report(@RequestBody VehicleDataRequest req) {
        vehicleDataService.report(req);
    }
}

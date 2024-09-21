package server.vehicle.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.vehicle.request.VehicleRequest;
import server.vehicle.service.VehicleService;
import server.vehicleData.request.VehicleDataRequest;

@RestController
@RequestMapping("/vehicle")
@Timed
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/add")
    public void add(@RequestBody VehicleRequest req) {
        vehicleService.add(req);
    }

    @GetMapping("/")
    public VehicleRequest add(@RequestParam String id) {
        return vehicleService.get(id);
    }
}

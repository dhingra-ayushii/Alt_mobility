package server.vehicle.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.exception.ChhalaangException;
import server.vehicle.model.InsuranceData;
import server.vehicle.model.Vehicle;
import server.vehicle.repository.VehicleRepository;
import server.vehicle.request.VehicleRequest;

import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public void add(VehicleRequest req) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(req.getId());
        if( vehicle.isPresent()){
            throw new ChhalaangException(" vehicle already added");
        }
        vehicleRepository.save(new Vehicle(req.getId(), new Gson().toJson(req.getInsurance())));
    }

    public VehicleRequest get(String id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(()-> new ChhalaangException("Incorrect id, vehicle not found"));
        InsuranceData insuranceData = new Gson().fromJson(vehicle.getInsurance(), InsuranceData.class);
        return new VehicleRequest(id, insuranceData);
    }
}

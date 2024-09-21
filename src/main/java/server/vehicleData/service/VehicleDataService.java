package server.vehicleData.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.vehicleData.model.VehicleData;
import server.vehicleData.repository.VehicleDataRepository;
import server.vehicleData.request.VehicleDataRequest;

@Service
public class VehicleDataService {
    @Autowired
    private VehicleDataRepository vehicleDataRepository;

    public void report(VehicleDataRequest req) {

        vehicleDataRepository.save(new VehicleData(req));
    }
}

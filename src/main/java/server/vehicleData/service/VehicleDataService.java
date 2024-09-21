package server.vehicleData.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.alert.service.AlertService;
import server.vehicleData.model.VehicleData;
import server.vehicleData.repository.VehicleDataRepository;
import server.vehicleData.request.VehicleDataRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleDataService {
    @Autowired
    private VehicleDataRepository vehicleDataRepository;

    @Autowired
    private AlertService alertService;



    public void report(VehicleDataRequest req) {

        VehicleData  vehicleData =  vehicleDataRepository.save(new VehicleData(req));
        alertService.checkThreshold(vehicleData);


    }

    public Double getMinBatteryVoltageLastHour(String vehicleId) {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        List<VehicleData> dataList = vehicleDataRepository.findByVehicleIdAndCreatedAtAfter(vehicleId, oneHourAgo);

        return dataList.stream()
                .map(VehicleData::getBatteryVoltage) // Assuming batteryVoltage is a String, convert it to Double
                .filter(voltage -> voltage != null) // Filter out null values
                .map(Double::parseDouble) // Convert String to Double
                .min(Double::compareTo) // Find minimum
                .orElse(null); // Return null if no data is found
    }
}

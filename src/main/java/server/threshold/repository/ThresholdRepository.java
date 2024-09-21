package server.threshold.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import server.threshold.model.AlertThreshold;

import java.util.List;

@Repository
public interface ThresholdRepository extends MongoRepository<AlertThreshold, String> {
    List<AlertThreshold> findByVehicleId(String vehicleId);

}

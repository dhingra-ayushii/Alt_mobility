package server.vehicleData.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import server.vehicleData.model.VehicleData;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleDataRepository extends MongoRepository<VehicleData, String> {
    @Query("{ 'createdAt': { $gte: ?1 }, 'vehicleId': ?0, 'batteryVoltage': { $exists: true } }")
    List<VehicleData> findByVehicleIdAndCreatedAtAfter(String vehicleId, LocalDateTime timestamp);

}

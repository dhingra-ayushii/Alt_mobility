package server.vehicleData.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import server.vehicleData.model.VehicleData;

@Repository
public interface VehicleDataRepository extends MongoRepository<VehicleData, String> {

}

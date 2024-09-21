package server.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import server.vehicle.model.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}

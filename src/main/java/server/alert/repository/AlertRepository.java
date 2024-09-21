package server.alert.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import server.alert.model.Alert;

public interface AlertRepository extends MongoRepository<Alert, String> {
}

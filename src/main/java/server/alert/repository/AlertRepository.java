package server.alert.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import server.alert.model.Alert;
import server.alert.model.AlertPercentage;

import java.util.List;


public interface AlertRepository extends MongoRepository<Alert, String> {

}

package server.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import server.user.model.FleetUser;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<FleetUser, String> {
    Optional<FleetUser> findByEmail(String email);
}

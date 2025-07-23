package in.ankeshkr.foodiesapi.repository;

import in.ankeshkr.foodiesapi.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity>findByEmail(String email);
}

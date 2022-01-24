package rs.ac.singidunum.nosqlispit.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.nosqlispit.entities.UserEntityMongodb;

public interface iMongoUserRepository extends MongoRepository<UserEntityMongodb, String> {
    UserEntityMongodb findByName(String name);
}

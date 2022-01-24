package rs.ac.singidunum.nosqlispit.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.nosqlispit.entities.FileEntityMongodb;

import java.util.List;

public interface iMongoFileRepository extends MongoRepository<FileEntityMongodb, String> {
    List<FileEntityMongodb> findAllByUser(String user);
}

package rs.ac.singidunum.nosqlispit.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.singidunum.nosqlispit.entities.FileEntityNeo4j;
import rs.ac.singidunum.nosqlispit.entities.UserEntityNeo4j;

import java.util.Collection;
import java.util.List;

public interface iNeo4jUserRepository extends Neo4jRepository<UserEntityNeo4j, Long> {
    @Query("match (u:UserEntityNeo4j) where u.name=$name return u;")
    UserEntityNeo4j findByName(String name);
}

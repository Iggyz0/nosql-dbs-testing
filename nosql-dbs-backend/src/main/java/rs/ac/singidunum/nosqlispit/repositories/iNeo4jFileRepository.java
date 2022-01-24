package rs.ac.singidunum.nosqlispit.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.singidunum.nosqlispit.entities.FileEntityNeo4j;

import java.util.List;

public interface iNeo4jFileRepository extends Neo4jRepository<FileEntityNeo4j, Long> {
    @Query("match (u:UserEntityNeo4j{name:$userName}) create (f:FileEntityNeo4j{name:$fileName})<-[:OWNS]-(u);")
    FileEntityNeo4j insertFile(String userName, String fileName);

    @Query("match (u:UserEntityNeo4j)-[:OWNS]-(f:FileEntityNeo4j) where u.name=$name return f")
    List<FileEntityNeo4j> readAllUserFiles(String name);
}

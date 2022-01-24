package rs.ac.singidunum.nosqlispit.entities;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class UserEntityNeo4j {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING)
    private List<FileEntityNeo4j> files;

    private UserEntityNeo4j() {};

    public UserEntityNeo4j(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileEntityNeo4j> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntityNeo4j> files) {
        this.files = files;
    }
}

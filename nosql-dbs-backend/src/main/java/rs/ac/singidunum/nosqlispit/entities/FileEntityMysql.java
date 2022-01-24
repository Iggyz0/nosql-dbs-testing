package rs.ac.singidunum.nosqlispit.entities;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileEntityMysql {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "varchar(64)")
    private String file_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}

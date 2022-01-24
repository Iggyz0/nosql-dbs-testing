package rs.ac.singidunum.nosqlispit.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_file")
public class UserFileEntityMysql {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "int")
    private int file_id;
    @Column(columnDefinition = "int")
    private int user_id;

    public UserFileEntityMysql(int file_id, int user_id) {
        this.file_id = file_id;
        this.user_id = user_id;
    }

    public UserFileEntityMysql() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

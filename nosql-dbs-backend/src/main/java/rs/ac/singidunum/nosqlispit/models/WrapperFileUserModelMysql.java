package rs.ac.singidunum.nosqlispit.models;

import rs.ac.singidunum.nosqlispit.entities.FileEntityMysql;
import rs.ac.singidunum.nosqlispit.entities.UserEntityMysql;

public class WrapperFileUserModelMysql {
    public UserEntityMysql user;
    public FileEntityMysql[] files;
}

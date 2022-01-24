package rs.ac.singidunum.nosqlispit.models;

import rs.ac.singidunum.nosqlispit.entities.FileEntityMongodb;
import rs.ac.singidunum.nosqlispit.entities.UserEntityMongodb;

public class WrapperFileUserModelMongo {
    public UserEntityMongodb user;
    public FileEntityMongodb[] files;
}

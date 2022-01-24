package rs.ac.singidunum.nosqlispit.models;

import rs.ac.singidunum.nosqlispit.entities.FileEntityNeo4j;
import rs.ac.singidunum.nosqlispit.entities.UserEntityNeo4j;

public class WrapperFileUserModelNeo4j {
    public UserEntityNeo4j user;
    public FileEntityNeo4j[] files;
}

package rs.ac.singidunum.nosqlispit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.nosqlispit.entities.*;
import rs.ac.singidunum.nosqlispit.models.WrapperFileUserModelMongo;
import rs.ac.singidunum.nosqlispit.models.WrapperFileUserModelMysql;
import rs.ac.singidunum.nosqlispit.models.WrapperFileUserModelNeo4j;
import rs.ac.singidunum.nosqlispit.services.FilesService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("files")
public class FilesController {
    @Autowired
    private FilesService filesService;

    @CrossOrigin(origins = "*")
    @PostMapping("writefilesmongo")
    public List<FileEntityMongodb> writeFilesMongodb(@RequestBody WrapperFileUserModelMongo wrapper) {
        return this.filesService.writeFilesMongodb(wrapper);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("readfilesmongo")
    public List<FileEntityMongodb> readFilesMongodb(@RequestBody UserEntityMongodb user) {
        return this.filesService.readAllFilesMongo(user);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("writefilesmysql")
    public List<FileEntityMysql> writeFilesMysql(@RequestBody WrapperFileUserModelMysql wrapper) {
        return this.filesService.writeFilesMysql(wrapper);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("readfilesmysql")
    public List<Optional<FileEntityMysql>> readFilesMysql(@RequestBody UserEntityMysql user) {
        return this.filesService.readAllFilesMysql(user);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("readfilesneo4j")
    public List<FileEntityNeo4j> readFilesNeo4j(@RequestBody UserEntityNeo4j user) {
        return this.filesService.findAllUserFiles(user);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("writefilesneo4j")
    public List<FileEntityNeo4j> writeFilesNeo4j(@RequestBody WrapperFileUserModelNeo4j wrapper) {
        return this.filesService.writeFilesNeo4j(wrapper);
    }
}

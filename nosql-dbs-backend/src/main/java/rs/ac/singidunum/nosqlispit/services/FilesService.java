package rs.ac.singidunum.nosqlispit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.nosqlispit.entities.*;
import rs.ac.singidunum.nosqlispit.models.WrapperFileUserModelMongo;
import rs.ac.singidunum.nosqlispit.models.WrapperFileUserModelMysql;
import rs.ac.singidunum.nosqlispit.models.WrapperFileUserModelNeo4j;
import rs.ac.singidunum.nosqlispit.repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilesService {
    @Autowired
    private iMysqlFileRepository mysqlFileRepo;
    @Autowired
    private iMysqlUserRepository mysqlUserRepo;
    @Autowired
    private iMysqlUserFileRepository mysqlUserFileRepo;

    @Autowired
    private iMongoFileRepository mongoFileRepo;
    @Autowired
    private iMongoUserRepository mongoUserRepo;

    @Autowired
    private iNeo4jUserRepository neo4jUserRepo;
    @Autowired
    private iNeo4jFileRepository neo4jFileRepo;


    // MongoDB methods
    public List<FileEntityMongodb> writeFilesMongodb(WrapperFileUserModelMongo wrapper) {
        UserEntityMongodb user = this.mongoUserRepo.findByName(wrapper.user.getName());
        List<FileEntityMongodb> writtenFiles = new ArrayList<FileEntityMongodb>();
        for (int i = 0; i < wrapper.files.length; i++) {
            wrapper.files[i].setUser(user.getName());
            writtenFiles.add(this.mongoFileRepo.insert(wrapper.files[i]));
        }
        return writtenFiles;
    }

    public List<FileEntityMongodb> readAllFilesMongo(UserEntityMongodb user) {
        return this.mongoFileRepo.findAllByUser(user.getName());
    }

    // MySQL methods
    public List<FileEntityMysql> writeFilesMysql(WrapperFileUserModelMysql wrapper) {
        UserEntityMysql user = this.mysqlUserRepo.findByName(wrapper.user.getName());
        List<FileEntityMysql> writtenFiles = new ArrayList<FileEntityMysql>();
        for (int i = 0; i < wrapper.files.length; i++) {
            writtenFiles.add(this.mysqlFileRepo.save(wrapper.files[i]));
        }

        for (FileEntityMysql file : writtenFiles) {
            this.mysqlUserFileRepo.save(new UserFileEntityMysql(file.getId(), user.getId()));
        }

        return writtenFiles;
    }

    public List<Optional<FileEntityMysql>> readAllFilesMysql(UserEntityMysql user) {
        UserEntityMysql dbUser = this.mysqlUserRepo.findByName(user.getName());
        List<UserFileEntityMysql> filesToGet = this.mysqlUserFileRepo.findAllByUser_id(dbUser.getId());
        List<Optional<FileEntityMysql>> foundFiles = new ArrayList<>();
        for (UserFileEntityMysql fileToGet : filesToGet) {
            foundFiles.add(this.mysqlFileRepo.findById(fileToGet.getId()));
        }
        return foundFiles;
    }

    // Neo4j methods
    public List<FileEntityNeo4j> findAllUserFiles(UserEntityNeo4j user) {
        return this.neo4jFileRepo.readAllUserFiles(user.getName());
    }

    public List<FileEntityNeo4j> writeFilesNeo4j(WrapperFileUserModelNeo4j wrapper) {
        UserEntityNeo4j dbUser = this.neo4jUserRepo.findByName(wrapper.user.getName());
        List<FileEntityNeo4j> writtenFiles = new ArrayList<>();
        for (int i = 0; i < wrapper.files.length; i++) {
            writtenFiles.add(this.neo4jFileRepo.insertFile(dbUser.getName(), wrapper.files[i].getName()));
        }
        return writtenFiles;
    }
}

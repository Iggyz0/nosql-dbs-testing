package rs.ac.singidunum.nosqlispit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.nosqlispit.entities.FileEntityMysql;

import java.util.List;

public interface iMysqlFileRepository extends JpaRepository<FileEntityMysql, Integer> {
}

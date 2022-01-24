package rs.ac.singidunum.nosqlispit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.singidunum.nosqlispit.entities.UserFileEntityMysql;

import java.util.List;

public interface iMysqlUserFileRepository extends JpaRepository<UserFileEntityMysql, Integer> {
    @Query("SELECT ufe FROM UserFileEntityMysql ufe WHERE ufe.user_id=:user_id")
    List<UserFileEntityMysql> findAllByUser_id(@Param("user_id") int id);
}

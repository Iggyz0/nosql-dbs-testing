package rs.ac.singidunum.nosqlispit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.nosqlispit.entities.UserEntityMysql;

public interface iMysqlUserRepository extends JpaRepository<UserEntityMysql, Integer> {
    UserEntityMysql findByName(String name);
}

package kg.aidar.fin_tech_innovators.repositories;

import kg.aidar.fin_tech_innovators.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from fin_tech_app_users where username = :username", nativeQuery = true)
    Optional<UserEntity> findUserByUsername(@Param("username") String username);

    @Query(value = "select count(id) from fin_tech_app_users where username = :username", nativeQuery = true)
    int isUserAlreadyExists(@Param("username") String username);

}

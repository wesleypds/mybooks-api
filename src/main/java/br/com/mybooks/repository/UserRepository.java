package br.com.mybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mybooks.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username =:username")
    UserEntity findByUsername(@Param("username") String username);

}

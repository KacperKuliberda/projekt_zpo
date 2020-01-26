package com.projekt_zpo.repositories;

import com.projekt_zpo.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("Select a from user where a.email=:email")
    User findByEmail(@Param("email") String email);
}

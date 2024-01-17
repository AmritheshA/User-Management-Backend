package com.example.User.ManagementBackend.Repository;

import com.example.User.ManagementBackend.Entity.Enum.Roles;
import com.example.User.ManagementBackend.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User,String> {

    boolean existsUserByUsername(String userName);
    List<User> findByRoleNot(Roles role);

    User findByEmail(String email);

    void deleteByEmail(String email);



}

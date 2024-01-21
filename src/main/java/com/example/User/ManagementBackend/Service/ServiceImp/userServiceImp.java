package com.example.User.ManagementBackend.Service.ServiceImp;

import com.example.User.ManagementBackend.Dto.UserDto;
import com.example.User.ManagementBackend.Entity.Enum.Roles;
import com.example.User.ManagementBackend.Entity.User;
import com.example.User.ManagementBackend.Repository.UserRepo;
import com.example.User.ManagementBackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class userServiceImp implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public userServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findByRoleNot(Roles.ADMIN);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());
        user.setProfileImageUrl(userDto.getProfileImageUrl());

        userRepo.save(user);
    }

    @Override
    public void removeUser(String email) {
        userRepo.deleteByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }

    @Override
    public void editUser(UserDto userDto, User user) {

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setProfileImageUrl(userDto.getProfileImageUrl());
        userRepo.save(user);
    }

    @Override
    public boolean ifUserNameExist(String username) {
        return userRepo.existsUserByUsername(username);
    }


}

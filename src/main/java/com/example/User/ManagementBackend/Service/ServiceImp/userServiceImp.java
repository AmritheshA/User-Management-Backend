package com.example.User.ManagementBackend.Service.ServiceImp;

import com.example.User.ManagementBackend.Dto.UserDto;
import com.example.User.ManagementBackend.Entity.Enum.Roles;
import com.example.User.ManagementBackend.Entity.User;
import com.example.User.ManagementBackend.Repository.UserRepo;
import com.example.User.ManagementBackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class userServiceImp implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public userServiceImp(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findByRoleNot(Roles.ADMIN);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Roles.USER);
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
    public void editUser(String newUser, User user) {

        user.setUsername(newUser);
        userRepo.save(user);
    }

    @Override
    public boolean ifUserNameExist(String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Override
    public void updateUserProfile(String url, String email) {

        User user = findByEmail(email);
        user.setProfileImageUrl(url);
        userRepo.save(user);
    }

    @Override
    public void removeProfilePic(String email) {

        User user = findByEmail(email);
        user.setProfileImageUrl("https://cdn.pixabay.com/photo/2023/09/22/12/18/profile-8268938_640.png");
        userRepo.save(user);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepo.existsByEmail(email);
    }


}

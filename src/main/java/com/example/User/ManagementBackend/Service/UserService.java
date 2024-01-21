package com.example.User.ManagementBackend.Service;

import com.example.User.ManagementBackend.Dto.UserDto;
import com.example.User.ManagementBackend.Entity.User;

import java.util.List;

public interface UserService {
   List<User> getAllUsers();

   void saveUser(UserDto userDto);

   void removeUser(String email);

   User findByEmail(String email);

   User findByUserName(String userName);

   void editUser(UserDto userDto, User user);

   boolean ifUserNameExist(String username);


}

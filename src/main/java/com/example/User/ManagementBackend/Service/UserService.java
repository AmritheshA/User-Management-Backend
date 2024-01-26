package com.example.User.ManagementBackend.Service;

import com.example.User.ManagementBackend.Dto.UserDto;
import com.example.User.ManagementBackend.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
   List<User> getAllUsers();

   void saveUser(UserDto userDto);

   void removeUser(String email);

   User findByEmail(String email);

   User findByUserName(String userName);

   void editUser(String newUserName, User user);

   boolean ifUserNameExist(String username);
   void updateUserProfile(String url, String email);

   void removeProfilePic(String email);

   boolean isEmailExist(String email);

}

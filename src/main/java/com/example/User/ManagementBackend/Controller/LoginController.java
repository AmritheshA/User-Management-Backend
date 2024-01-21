package com.example.User.ManagementBackend.Controller;

import com.example.User.ManagementBackend.Dto.UserDto;
import com.example.User.ManagementBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> SignUp(@RequestBody UserDto userDto){

        if(userService.ifUserNameExist(userDto.getUsername())){
            return new ResponseEntity<>("User Already Exist",HttpStatus.CONFLICT);
        }
       userService.saveUser(userDto);

       return new ResponseEntity<>("Success",HttpStatus.CREATED);
   }
}

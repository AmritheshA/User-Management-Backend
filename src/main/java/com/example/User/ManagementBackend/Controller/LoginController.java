package com.example.User.ManagementBackend.Controller;

import com.example.User.ManagementBackend.Controller.AuthController.AuthControllerMethods;
import com.example.User.ManagementBackend.Controller.AuthController.RequestData;
import com.example.User.ManagementBackend.Dto.UserDto;
import com.example.User.ManagementBackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    private final UserService userService;
    private final AuthControllerMethods authController;


    @Autowired
    public LoginController(UserService userService, AuthControllerMethods authController) {
        this.userService = userService;
        this.authController = authController;
    }


    @PostMapping("/login")
    public ResponseEntity<?> createResponseToken(@RequestBody RequestData requestData) throws Exception {

        log.info("userName " + requestData.getEmail());
        log.info("password " + requestData.getPassword());
        return authController.createResponseToken(requestData);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> SignUp(@RequestBody UserDto userDto) throws Exception {

        if (userService.isEmailExist(userDto.getEmail())) {
            log.info("Email Already Exit");
            return new ResponseEntity<>("Email Already Exist", HttpStatus.CONFLICT);
        }
        userService.saveUser(userDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);


    }

}

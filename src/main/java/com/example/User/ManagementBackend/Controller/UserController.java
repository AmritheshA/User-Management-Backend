package com.example.User.ManagementBackend.Controller;

import com.example.User.ManagementBackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/updatePic")
    public ResponseEntity<?> updateProfilePic(@RequestBody String url,@RequestParam("email") String email) {

        userService.updateUserProfile(url,email);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @GetMapping("/remove-image")
    public ResponseEntity<?> removeProfilePic(@RequestParam("email") String email){
        userService.removeProfilePic(email);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}

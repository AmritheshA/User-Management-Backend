package com.example.User.ManagementBackend.Controller;

import com.example.User.ManagementBackend.Dto.UserDto;
import com.example.User.ManagementBackend.Entity.User;
import com.example.User.ManagementBackend.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //    Get all the users
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //    Add user
    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(userDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    //    Remove user
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> removeUser(@Valid @RequestParam("email") String email) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Auth" + authentication);


        userService.removeUser(email);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // Edit user
    @PutMapping("/edit-user")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

        User user = userService.findByEmail(userDto.getEmail());

        log.info("error status" + result.hasErrors());

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        userService.editUser(userDto, user);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}

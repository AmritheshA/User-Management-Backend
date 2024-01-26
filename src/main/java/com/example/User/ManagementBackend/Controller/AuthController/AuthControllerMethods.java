package com.example.User.ManagementBackend.Controller.AuthController;

import com.example.User.ManagementBackend.Entity.User;
import com.example.User.ManagementBackend.Service.JWT.JwtService;
import com.example.User.ManagementBackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthControllerMethods {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final UserService userService;


    @Autowired
    public AuthControllerMethods(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }


    public ResponseEntity<?> createResponseToken(RequestData requestData) throws Exception {

        User user = userService.findByEmail(requestData.getEmail());

        Authentication authentication;
        try {

            authentication =
                    authenticationManager
                            .authenticate(
                                    new UsernamePasswordAuthenticationToken(user.getEmail(), requestData.getPassword()));

        } catch (BadCredentialsException exception) {
            log.info("Invalid Credential");
            return new ResponseEntity<>("Invalid Credential",HttpStatus.UNAUTHORIZED);
        }


        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwtToken = jwtService.generateToken(authentication,requestData.getEmail());


        log.info("Token Generated...");
        return new ResponseEntity<>(new ResponseData(jwtToken,user), HttpStatus.OK);
    }

}

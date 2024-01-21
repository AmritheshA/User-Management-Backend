package com.example.User.ManagementBackend.Controller.AuthController;

import com.example.User.ManagementBackend.Service.JWT.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseData> createResponseToken(@RequestBody RequestData requestData) throws Exception {
        Authentication authentication;
        try {
            authentication =
                    authenticationManager
                            .authenticate(
                                    new UsernamePasswordAuthenticationToken(requestData.getUserName(), requestData.getPassword()));

        } catch (BadCredentialsException exception) {
            throw new Exception("Invalid Credential");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwtToken = jwtService.generateToken(authentication);

        log.info("Token Generated....");

        return new ResponseEntity<>(new ResponseData(jwtToken), HttpStatus.OK);
    }

}

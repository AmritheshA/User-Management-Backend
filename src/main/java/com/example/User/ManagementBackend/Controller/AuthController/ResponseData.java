package com.example.User.ManagementBackend.Controller.AuthController;


import com.example.User.ManagementBackend.Entity.User;
import lombok.Getter;

@Getter
public class ResponseData {

    private final String jwtToken;
    private final User user;

    public ResponseData(String jwtToken, User user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

}
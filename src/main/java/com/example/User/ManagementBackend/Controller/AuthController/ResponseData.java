package com.example.User.ManagementBackend.Controller.AuthController;


import lombok.Getter;
import lombok.Setter;

@Getter
public class ResponseData {

    private final String jwtToken;

    public ResponseData(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
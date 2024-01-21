package com.example.User.ManagementBackend.Controller.AuthController;


import lombok.Getter;

@Getter
public class ResponseData {

    private final String jwtToken;

    public ResponseData(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
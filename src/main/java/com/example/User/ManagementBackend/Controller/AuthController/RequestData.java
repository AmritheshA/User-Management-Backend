package com.example.User.ManagementBackend.Controller.AuthController;

import lombok.Getter;

@Getter
public class RequestData {

    private final String userName;
    private final String password;

    public RequestData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}

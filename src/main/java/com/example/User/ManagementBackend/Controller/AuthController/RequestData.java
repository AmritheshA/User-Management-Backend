package com.example.User.ManagementBackend.Controller.AuthController;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestData {

    private final String email;
    private final String password;

    public RequestData(String email, String password) {
        this.email = email;
        this.password = password;
    }

}

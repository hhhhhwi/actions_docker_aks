package com.hwiyeon.actions_docker_aks.login.dto;

public class LoginResponse {
    private String name;

    public LoginResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

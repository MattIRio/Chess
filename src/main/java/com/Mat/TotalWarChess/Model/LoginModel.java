package com.Mat.TotalWarChess.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginModel {

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 12, message = "Username must be from 3 to 12 characters")
    private String username;

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 12, message = "Username must be from 3 to 12 characters")
    private String secondUsername;

    public LoginModel() {}

    public LoginModel(String username, String secondUsername) {
        this.username = username;
        this.secondUsername = secondUsername;
    }

    // Убираем аннотации из методов
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecondUsername() {
        return secondUsername;
    }

    public void setSecondUsername(String secondUsername) {
        this.secondUsername = secondUsername;
    }
}
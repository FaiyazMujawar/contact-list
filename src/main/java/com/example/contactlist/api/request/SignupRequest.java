package com.example.contactlist.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupRequest {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
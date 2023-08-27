package com.example.contactlist.api.controllers;

import com.example.contactlist.api.request.LoginRequest;
import com.example.contactlist.api.request.SignupRequest;
import com.example.contactlist.api.response.AuthResponse;
import com.example.contactlist.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public AuthResponse signup(@RequestBody @Valid SignupRequest request) {
        return authService.userSignup(request);
    }

    @PostMapping(path = "/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

}
package com.example.contactlist.services;

import com.example.contactlist.api.request.LoginRequest;
import com.example.contactlist.api.request.SignupRequest;
import com.example.contactlist.api.response.AuthResponse;
import com.example.contactlist.entities.User;
import com.example.contactlist.exceptions.ApiException;
import com.example.contactlist.mappers.UserMapper;
import com.example.contactlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public AuthResponse userSignup(SignupRequest request) {
        var user = userMapper.toUser(request);
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        return getAuthResponse(user);
    }

    private AuthResponse getAuthResponse(User user) {
        Map<String, ?> payload = Map.of();
        var token = jwtService.signToken(user.getEmail(), payload, null);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new ApiException(NOT_FOUND, "User not found"));
            return getAuthResponse(user);
        } catch (AuthenticationException e) {
            throw new ApiException(BAD_REQUEST, "Bad credentials");
        }
    }
}
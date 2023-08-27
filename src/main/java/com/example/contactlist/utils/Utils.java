package com.example.contactlist.utils;

import com.example.contactlist.entities.User;
import com.example.contactlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utils {
    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        var email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email).get();
    }
}
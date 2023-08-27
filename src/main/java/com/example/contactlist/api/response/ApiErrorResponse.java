package com.example.contactlist.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Getter
@Builder
public class ApiErrorResponse {
    private String message;
    private Collection<?> errors;
}
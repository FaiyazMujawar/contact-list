package com.example.contactlist.mappers;

import com.example.contactlist.api.request.SignupRequest;
import com.example.contactlist.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toUser(SignupRequest request);
}
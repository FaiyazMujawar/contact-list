package com.example.contactlist.mappers;

import com.example.contactlist.api.request.ContactRequest;
import com.example.contactlist.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {
    Contact toContact(ContactRequest request);

    Contact updateContact(@MappingTarget Contact contact, ContactRequest request);
}
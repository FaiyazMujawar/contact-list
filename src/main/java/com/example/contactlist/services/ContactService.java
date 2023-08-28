package com.example.contactlist.services;

import com.example.contactlist.api.request.ContactRequest;
import com.example.contactlist.entities.Contact;
import com.example.contactlist.exceptions.ApiException;
import com.example.contactlist.mappers.ContactMapper;
import com.example.contactlist.repositories.ContactRepository;
import com.example.contactlist.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.contactlist.entities.Status.ACTIVE;
import static com.example.contactlist.entities.Status.INACTIVE;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final Utils utils;
    private final ContactMapper mapper;

    public List<Contact> getAll() {
        return contactRepository.findAllByUser(utils.getAuthenticatedUser());
    }

    public Contact create(ContactRequest request) {
        var contact = mapper.toContact(request);
        contact.setUser(utils.getAuthenticatedUser());
        return contactRepository.save(contact);
    }

    public Contact update(UUID id, ContactRequest request) {
        var contact = getById(id);
        contact = mapper.updateContact(contact, request);
        return contactRepository.save(contact);
    }

    public Contact getById(UUID id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Contact with id {%s} not found".formatted(id)));
    }

    public Contact toggleStatus(UUID id) {
        var contact = getById(id);
        contact.setStatus(contact.getStatus().equals(ACTIVE) ? INACTIVE : ACTIVE);
        return contactRepository.save(contact);
    }

    public void delete(UUID id) {
        if (!contactRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, ("Contact with id {%s} not found").formatted(id));
        }
        contactRepository.deleteById(id);
    }
}
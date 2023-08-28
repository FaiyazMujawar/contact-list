package com.example.contactlist.repositories;

import com.example.contactlist.entities.Contact;
import com.example.contactlist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findAllByUser(User user);
}
package com.example.contactlist.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.contactlist.entities.Status.ACTIVE;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer contact;
    @Enumerated
    @Builder.Default
    private Status status = ACTIVE;
    @ManyToOne
    @JsonIgnore
    private User user;
}
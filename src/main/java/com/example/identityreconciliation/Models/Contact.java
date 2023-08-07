package com.example.identityreconciliation.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "Contacts")
@Data
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String email;

    @Column(name = "linked_id", nullable = true)
    private Integer linkedId;

    @Column(name = "link_precedence")
    private String linkPrecedence;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @Column(name = "record_status")
    private Boolean recordStatus;

    private Contact(String phoneNumber, String email, Integer linkedId, String linkPrecedence) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.linkedId = linkedId;
        this.linkPrecedence = linkPrecedence;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        this.recordStatus = true;
    }

    public Contact createFirstInstance(String phoneNumber, String email) {
        return new Contact(phoneNumber, email, null, "primary");
    }

    public Contact createNthInstance(String phoneNumber, String email, Integer linkedId) {
        return new Contact(phoneNumber, email, linkedId, "secondary");
    }

}

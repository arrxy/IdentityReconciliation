package com.example.identityreconciliation.Repository;

import com.example.identityreconciliation.Models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Contact, Long> {

    List<Contact> findByEmail(String email);
    List<Contact> findByPhoneNumber(String phoneNumber);
    List<Contact> findAllByEmail(String email);
    List<Contact> findAllByPhoneNumber(String phoneNumber);
    Contact findByEmailAndPhoneNumber(String email, String phoneNumber);

    @Query("SELECT c FROM Contact c WHERE c.email = ?1 or c.phoneNumber = ?2")
    List<Contact> findAllByEmailOrPhoneNumber(String email, String phoneNumber);

}

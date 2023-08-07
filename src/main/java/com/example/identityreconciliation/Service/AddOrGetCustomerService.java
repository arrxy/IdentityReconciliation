package com.example.identityreconciliation.Service;

import com.example.identityreconciliation.Dto.ContactDto;
import com.example.identityreconciliation.Dto.IdentifyReqDto;
import com.example.identityreconciliation.Dto.IdentifyResponseDto;
import com.example.identityreconciliation.Models.Contact;
import com.example.identityreconciliation.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddOrGetCustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public IdentifyResponseDto fetchOrSave(IdentifyReqDto identifyReq) {
        String phoneNumber = identifyReq.getPhoneNumber();
        String email = identifyReq.getEmail();
        if (phoneNumber != null && email != null) {
            saveCustomer(phoneNumber, email);
            return null;
        }
        List<Contact> list = new ArrayList<>();
        if (phoneNumber == null) {
            list = customerRepo.findByEmail(email);
        } else {
            list = customerRepo.findByPhoneNumber(phoneNumber);
        }
        return generateResponse(list);
    }

    private IdentifyResponseDto generateResponse(List<Contact> contacts) {
        ContactDto contactDto = new ContactDto();
        for (var contact: contacts) {
            contactDto.getEmails().add(contact.getEmail());
            contactDto.getPhoneNumbers().add(contact.getPhoneNumber());
            if (contact.getLinkedId() != null) {
                contactDto.getSecondaryContactIds().add(contact.getId());
            } else {
                contactDto.setPrimaryContatctId(contact.getId());
            }
        }
        return new IdentifyResponseDto(contactDto);
    }
    private Contact saveCustomer(String phoneNumber, String email) {
        List<Contact> contact = customerRepo.findAllByEmailOrPhoneNumber(email, phoneNumber);
        if (contact.isEmpty()) {
            return saveFirstCustomerEntity(phoneNumber, email);
        }
        int linkedId = Optional.ofNullable(contact.get(0).getLinkedId()).orElse(contact.get(0).getId());
        return saveNthCustomerEntity(phoneNumber, email, linkedId);
    }

    private Contact saveFirstCustomerEntity(String phoneNumber, String email) {
        Contact contact = new Contact();
        contact = contact.createFirstInstance(phoneNumber, email);
        return saveCustomer(contact);
    }

    private Contact saveNthCustomerEntity(String phoneNumber, String email, int linkedId) {
//        Contact contact = customerRepo.findAllByEmailOrPhoneNumber(email, phoneNumber);
        Contact contact = new Contact();
        contact = contact.createNthInstance(phoneNumber, email, linkedId);
        return saveCustomer(contact);
    }

    private Contact saveCustomer(Contact contact) {
        return customerRepo.save(contact);
    }

}

package com.example.identityreconciliation.Dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ContactDto {
    private Integer primaryContatctId;
    private Set<String> emails = new HashSet<>();
    private Set<String> phoneNumbers = new HashSet<>();
    private Set<Integer> secondaryContactIds = new HashSet<>();
}

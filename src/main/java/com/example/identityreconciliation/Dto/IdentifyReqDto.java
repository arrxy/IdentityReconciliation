package com.example.identityreconciliation.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifyReqDto {
    private String email;
    private String phoneNumber;
}

package com.example.identityreconciliation.ExceptionHandler.Responses;

import lombok.Data;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Data
public class BaseResponse {
    private boolean success;

    private ZonedDateTime date = ZonedDateTime.now(ZoneOffset.UTC);

    private String tenant;

    private String traceId;
}

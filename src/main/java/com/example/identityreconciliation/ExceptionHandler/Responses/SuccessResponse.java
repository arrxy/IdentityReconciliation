package com.example.identityreconciliation.ExceptionHandler.Responses;

import lombok.Data;

@Data
public class SuccessResponse<T> extends BaseResponse {

    private T data;

    public SuccessResponse() {
        super.setSuccess(true);
    }

    public SuccessResponse(T data) {
        super.setSuccess(true);
        this.data=data;
    }

}

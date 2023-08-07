package com.example.identityreconciliation.Controller;

import com.example.identityreconciliation.Dto.IdentifyReqDto;
import com.example.identityreconciliation.ExceptionHandler.Responses.SuccessResponse;
import com.example.identityreconciliation.Service.AddOrGetCustomerService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/customer")
public class identifyReconciliationController {

    @Autowired
    private AddOrGetCustomerService addOrGetCustomerService;

    @PostMapping("/identify")
    public ResponseEntity<?> identifyCustomer(@Valid @RequestBody IdentifyReqDto identifyReq) {
        return ResponseEntity.ok(new SuccessResponse<>(addOrGetCustomerService.fetchOrSave(identifyReq)));
    }
}

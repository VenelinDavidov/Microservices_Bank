package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.ResponseRequest;
import com.eazybytes.accounts.service.IAccountsService;


import jakarta.validation.Valid;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    @Autowired
    private IAccountsService iAccountsService;


    @PostMapping("/create")
    public ResponseEntity <ResponseRequest> createAccount(@Valid @RequestBody CustomerRequest customerDto) {

        iAccountsService.createAccount (customerDto);

        return ResponseEntity
                .status (HttpStatus.CREATED)
                .body (new ResponseRequest (AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }


    @PutMapping("/update")
    public ResponseEntity <ResponseRequest> updateAccountDetails(@Valid @RequestBody CustomerRequest customerDto) {

        boolean isUpdate = iAccountsService.updateAccount (customerDto);
        if (isUpdate) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseRequest (AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {

            return ResponseEntity
                    .status (HttpStatus.INTERNAL_SERVER_ERROR)
                    .body (new ResponseRequest (AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }

    }


    @GetMapping("/fetch")
    public ResponseEntity <CustomerRequest> fetchAccountDetails(@RequestParam
                                                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
                                                                    String mobileNumber) {
        CustomerRequest customerRequest = iAccountsService.fetchAccountDetails (mobileNumber);

        return ResponseEntity
                .status (HttpStatus.OK)
                .body (customerRequest);
    }




    @DeleteMapping("/delete")
    public ResponseEntity <ResponseRequest> deleteAccountDetails(@RequestParam
                                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
                                                                     String mobileNumber) {

        boolean isDeleted = iAccountsService.deleteAccount (mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseRequest (AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status (HttpStatus.INTERNAL_SERVER_ERROR)
                    .body (new ResponseRequest (AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
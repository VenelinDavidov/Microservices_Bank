package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.ResponseRequest;
import com.eazybytes.accounts.service.IAccountsService;


import jakarta.validation.Valid;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    @Autowired
    private  IAccountsService iAccountsService;


    @PostMapping("/create")
    public ResponseEntity<ResponseRequest> createAccount(@Valid @RequestBody CustomerRequest customerDto) {

        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseRequest (AccountConstants.STATUS_201 , AccountConstants.MESSAGE_201));
    }


}

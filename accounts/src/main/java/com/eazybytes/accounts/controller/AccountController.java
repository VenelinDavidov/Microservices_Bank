package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.ResponseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {


    @PostMapping( "/create")
    public ResponseEntity <ResponseRequest> createAccount(@RequestBody CustomerRequest customerRequest) {

        return ResponseEntity
                .status (HttpStatus.CREATED)
                .body (new ResponseRequest (AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }
}

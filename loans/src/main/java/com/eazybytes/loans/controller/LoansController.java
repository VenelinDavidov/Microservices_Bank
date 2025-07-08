package com.eazybytes.loans.controller;


import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansRequest;
import com.eazybytes.loans.dto.ResponseLoansDto;
import com.eazybytes.loans.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

    @Autowired
    private  ILoansService loansService;


    @PostMapping("/create")
    public ResponseEntity<ResponseLoansDto> createLoans (
                                                       @Valid
                                                       @Pattern (regexp = "^[0-9]{10}$", message = "Mobile number should be of 10 digits")
                                                       @RequestParam String mobileNumber) {

        loansService.createLoans(mobileNumber);

        return ResponseEntity
                .status (HttpStatus.CREATED)
                .body (new ResponseLoansDto (LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));

    }





    @GetMapping("/fetch")
    public ResponseEntity<LoansRequest> fetchLoansDetails (
                                                            @RequestParam
                                                            @Pattern (regexp = "^[0-9]{10}$", message = "Mobile number should be of 10 digits")
                                                            String mobileNumber) {

        LoansRequest loansRequest = loansService.fetchLoans (mobileNumber);

        return ResponseEntity
                .status (HttpStatus.OK)
                .body (loansRequest);
    }



}

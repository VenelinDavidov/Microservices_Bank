package com.eazybytes.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseLoansDto {

    private String statusCode;

    private String statusMsg;
}

package com.eazybytes.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoansResponse {

    private String statusCode;

    private String statusMsg;
}

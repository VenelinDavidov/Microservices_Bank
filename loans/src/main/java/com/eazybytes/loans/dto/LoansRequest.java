package com.eazybytes.loans.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoansRequest {


    private String loanNumber;

    private String mobileNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;
}

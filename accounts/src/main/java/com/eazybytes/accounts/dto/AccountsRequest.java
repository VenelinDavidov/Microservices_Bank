package com.eazybytes.accounts.dto;


import lombok.Data;

@Data
public class AccountsRequest {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}

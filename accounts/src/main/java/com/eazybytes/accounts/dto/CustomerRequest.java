package com.eazybytes.accounts.dto;


import lombok.Data;

@Data
public class CustomerRequest {

    private String name;

    private String email;

    private String mobileNumber;

    private AccountsRequest accountsDto;
}

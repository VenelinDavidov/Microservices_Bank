package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsRequest {

    @NotEmpty(message = "Name should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be of 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type should not be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch address should not be null or empty")
    private String branchAddress;
}

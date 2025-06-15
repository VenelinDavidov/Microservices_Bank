package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
    private String mobileNumber;

    private AccountsRequest accountsDto;
}

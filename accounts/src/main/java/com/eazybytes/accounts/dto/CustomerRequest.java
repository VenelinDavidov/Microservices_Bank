
package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
        )
public class CustomerRequest {



    @Schema(
            description = "Customer", example = "Easy Bytes"
    )
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;



    @Schema(
            description = "Email address for customer", example = "jRbG2@example.com"
    )
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;



    @Schema(
            description = "Mobile number for customer", example = "1234567890"
    )
    @NotEmpty(message = "Mobile number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
    private String mobileNumber;



    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsRequest accountsDto;
}

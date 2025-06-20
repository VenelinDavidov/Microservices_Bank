
package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Accounts information")
public class AccountsRequest {

    @NotEmpty(message = "Name should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be of 10 digits")
    @Schema(
            description = "Account number for EasyBank account", example = "4546789012"
     )
    private Long accountNumber;



    @NotEmpty(message = "Account type should not be null or empty")
    @Schema(
            description = "Account type for EasyBank account", example = "Savings"
    )
    private String accountType;



    @NotEmpty(message = "Branch address should not be null or empty")
    @Schema(
            description = "Branch address for EasyBank", example = "№10 Vratsa, Bulgaria"
    )
    private String branchAddress;
}

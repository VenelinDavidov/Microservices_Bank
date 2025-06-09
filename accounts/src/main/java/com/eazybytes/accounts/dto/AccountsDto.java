package com.eazybytes.accounts.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountsDto {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}

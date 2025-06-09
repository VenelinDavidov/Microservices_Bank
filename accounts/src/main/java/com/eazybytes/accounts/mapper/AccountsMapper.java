package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsRequest;
import com.eazybytes.accounts.entity.Accounts;


public class AccountsMapper {

    public static AccountsRequest mapToAccountsDto(Accounts accounts, AccountsRequest accountsDto) {

        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsRequest accountsDto, Accounts accounts) {

        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

}

package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsRequest;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {


    // from entity to dto
    public static AccountsRequest mapToAccountsRequest(Accounts accounts, AccountsRequest accountsRequest) {

        accountsRequest.setAccountNumber(accounts.getAccountNumber());
        accountsRequest.setAccountType(accounts.getAccountType());
        accountsRequest.setBranchAddress(accounts.getBranchAddress());

        return accountsRequest;

    }
    // from dto to entity
    public static Accounts mapToAccounts(AccountsRequest accountsRequest, Accounts accounts) {

        accounts.setAccountNumber(accountsRequest.getAccountNumber());
        accounts.setAccountType(accountsRequest.getAccountType());
        accounts.setBranchAddress(accountsRequest.getBranchAddress());

        return accounts;
    }

}

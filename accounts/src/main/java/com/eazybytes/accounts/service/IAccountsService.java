package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerRequest;

public interface IAccountsService {

    void createAccount(CustomerRequest customerRequest);
}

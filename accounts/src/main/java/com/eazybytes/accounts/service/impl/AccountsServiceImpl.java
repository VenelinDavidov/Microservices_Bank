package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

   private AccountsRepository accountsRepository;
   private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerRequest customerRequest) {

    }
}

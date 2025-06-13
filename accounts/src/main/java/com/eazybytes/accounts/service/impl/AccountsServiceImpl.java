package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.AccountsRequest;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements IAccountsService {



   private final  AccountsRepository accountsRepository;
   private final  CustomerRepository customerRepository;



   @Autowired
    public AccountsServiceImpl(AccountsRepository accountsRepository,
                               CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }




    @Override
    public void createAccount(CustomerRequest customerRequest) {

        Customer customer = CustomerMapper.mapToCustomer(customerRequest, new Customer());

        Optional<Customer> optionalCustomer = customerRepository
                .findByMobileNumber(customerRequest.getMobileNumber());

        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException ("Customer already exists, with given mobileNumber " + customerRequest.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy ("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    private Accounts createNewAccount(Customer customer) {

        Accounts newAccount = new Accounts();
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");

        return newAccount;
    }


    @Override
    public boolean updateAccount(CustomerRequest customerDto) {

        boolean isUpdate = false;

        AccountsRequest accountsRequest = customerDto.getAccountsDto ();
        if (accountsRequest != null){

            Accounts accounts = accountsRepository
                    .findById (accountsRequest.getAccountNumber ())
                    .orElseThrow (() -> new ResourceNotFoundException ("Account", "AccountNumber", accountsRequest.getAccountNumber ().toString ()));

            AccountsMapper.mapToAccounts (accountsRequest, accounts);
            accounts = accountsRepository.save (accounts);


            Long customerId = accounts.getCustomerId ();
            Customer customer = customerRepository.findById (customerId)
                    .orElseThrow (() -> new ResourceNotFoundException ("Customer", "CustomerID", customerId.toString ()));

            CustomerMapper.mapToCustomer (customerDto, customer);
            customerRepository.save (customer);

            isUpdate = true;
        }

        return isUpdate;
    }




    @Override
    public CustomerRequest fetchAccountDetails(String mobileNumber) {

       // check mobile number or not exist trow exception
        Customer customer = customerRepository.findByMobileNumber (mobileNumber)
                .orElseThrow (() -> new ResourceNotFoundException ("Customer", "MobileNumber", mobileNumber));

        // check customer id or not exist trow exception
        Accounts accounts = accountsRepository.findByCustomerId (customer.getCustomerId ())
                .orElseThrow (() -> new ResourceNotFoundException ("Account", "CustomerId", customer.getCustomerId ().toString ()));


        CustomerRequest customerRequest = CustomerMapper.mapToCustomerDto (customer, new CustomerRequest ());
        customerRequest.setAccountsDto (AccountsMapper.mapToAccountsDto (accounts, new AccountsRequest ()));

        return customerRequest;
    }

}

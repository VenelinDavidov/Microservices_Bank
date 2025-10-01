package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountsRequest;
import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.LoansRequest;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomerService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

     private AccountsRepository accountsRepository;
     private CustomerRepository customerRepository;
     private CardsFeignClient cardsFeignClient;
     private LoansFeignClient loansFeignClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {


        Customer customer = customerRepository.findByMobileNumber (mobileNumber)
                .orElseThrow (() -> new ResourceNotFoundException ("Customer", "MobileNumber", mobileNumber)); // check mobile number or not exist trow exception

        Accounts accounts = accountsRepository.findByCustomerId (customer.getCustomerId ())
                .orElseThrow (() -> new ResourceNotFoundException ("Account", "CustomerId", customer.getCustomerId ().toString ())); // check customer id or not exist trow exception


        CustomerDetailsDto  customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto (customer, new CustomerDetailsDto ()); //Dto
        customerDetailsDto.setAccountsDto (AccountsMapper.mapToAccountsDto (accounts, new AccountsRequest ()));

        ResponseEntity <LoansRequest> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails (correlationId,mobileNumber); // calling feign loan service
        customerDetailsDto.setLoansDto (loansDtoResponseEntity.getBody ());

        ResponseEntity <CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails (correlationId,mobileNumber); // calling feign cards service
        customerDetailsDto.setCardsDto (cardsDtoResponseEntity.getBody ());

        return customerDetailsDto;
    }
}

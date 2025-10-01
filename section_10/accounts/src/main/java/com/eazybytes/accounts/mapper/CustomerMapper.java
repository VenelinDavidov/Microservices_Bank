package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {


     public static CustomerRequest mapToCustomerDto(Customer customer, CustomerRequest customerDto) {
         customerDto.setName(customer.getName());
         customerDto.setEmail(customer.getEmail());
         customerDto.setMobileNumber(customer.getMobileNumber());
         return customerDto;
     }

    public static Customer mapToCustomer(CustomerRequest customerRequest, Customer customer) {
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setMobileNumber(customerRequest.getMobileNumber());
        return customer;
    }


    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;
    }
}

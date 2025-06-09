package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {



     public static CustomerRequest mapToCustomerDto(Customer customer, CustomerRequest customerDto) {
         customerDto.setName(customer.getName());
         customerDto.setEmail(customer.getEmail());
         customerDto.setMobileNumber(customer.getMobileNumber());
         return customerDto;
     }

    public static Customer mapToCustomer(CustomerRequest customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}

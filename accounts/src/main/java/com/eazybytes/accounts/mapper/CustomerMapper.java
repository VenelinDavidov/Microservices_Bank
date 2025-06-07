package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {


     // from entity to dto
    public static CustomerRequest mapToCustomerRequest(Customer customer, CustomerRequest customerRequest) {

        customerRequest.setName (customer.getName ());
        customerRequest.setEmail (customer.getEmail ());
        customerRequest.setMobileNumber (customer.getMobileNumber ());

        return customerRequest;
    }


    // from dto to entity
    public static Customer mapToCustomer(CustomerRequest customerRequest, Customer customer) {

        customer.setName (customerRequest.getName ());
        customer.setEmail (customerRequest.getEmail ());
        customer.setMobileNumber (customerRequest.getMobileNumber ());

        return customer;
    }
}

package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.dto.LoansRequest;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;





    @Override
    public void createLoans(String mobileNumber) {

    }

    @Override
    public LoansRequest fetchLoans(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateLoans(LoansRequest loansDto) {
        return false;
    }

    @Override
    public boolean deleteLoans(String mobileNumber) {
        return false;
    }
}

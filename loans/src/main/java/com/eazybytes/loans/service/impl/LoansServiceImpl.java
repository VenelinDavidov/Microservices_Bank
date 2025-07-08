package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansRequest;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;





    @Override
    public void createLoans(String mobileNumber) {

        loansRepository.findByMobileNumber (mobileNumber)
                .ifPresent (loans -> {throw new LoanAlreadyExistsException ("Loan already registered with given %s ".formatted (mobileNumber));});

         loansRepository.save (createNewLoans (mobileNumber));

    }

    private Loans createNewLoans(String mobileNumber) {


        long randomLoanNumber = 100000000000L + new Random ().nextInt(900000000);
        Loans loans = Loans.builder ()
                .mobileNumber (mobileNumber)
                .loanNumber (Long.toString (randomLoanNumber))
                .loanType (LoansConstants.HOME_LOAN)
                .totalLoan (LoansConstants.NEW_LOAN_LIMIT)
                .amountPaid (0)
                .outstandingAmount (LoansConstants.NEW_LOAN_LIMIT)
                .build ();

        return loans;
    }

    @Override
    public LoansRequest fetchLoans(String mobileNumber) {

        Loans loans = loansRepository
                .findByMobileNumber (mobileNumber)
                .orElseThrow (() -> new ResourceNotFoundException ("Loan", "MobileNumber", mobileNumber));

        return LoansMapper.mapToLoansRequest (loans, new LoansRequest ());
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

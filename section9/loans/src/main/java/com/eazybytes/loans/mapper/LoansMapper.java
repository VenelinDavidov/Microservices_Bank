package com.eazybytes.loans.mapper;


import com.eazybytes.loans.dto.LoansRequest;
import com.eazybytes.loans.entity.Loans;

public class LoansMapper {


    // from dto to entity
    public static Loans mapToLoans(LoansRequest loansDto, Loans loans) {
        loans.setLoanNumber (loansDto.getLoanNumber ());
        loans.setMobileNumber (loansDto.getMobileNumber ());
        loans.setLoanType (loansDto.getLoanType ());
        loans.setTotalLoan (loansDto.getTotalLoan ());
        loans.setAmountPaid (loansDto.getAmountPaid ());
        loans.setOutstandingAmount (loansDto.getOutstandingAmount ());
        return loans;
    }

   // from entity to dto
    public static LoansRequest mapToLoansRequest(Loans loans, LoansRequest loansDto) {
        loansDto.setMobileNumber (loans.getMobileNumber ());
        loansDto.setLoanNumber (loans.getLoanNumber ());
        loansDto.setLoanType (loans.getLoanType ());
        loansDto.setTotalLoan (loans.getTotalLoan ());
        loansDto.setAmountPaid (loans.getAmountPaid ());
        loansDto.setOutstandingAmount (loans.getOutstandingAmount ());
        return loansDto;

    }

}

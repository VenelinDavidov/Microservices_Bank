package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansRequest;

public interface ILoansService {

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoans(String mobileNumber);


    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    LoansRequest fetchLoans(String mobileNumber);

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */

    boolean updateLoans(LoansRequest loansDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */

    boolean deleteLoans(String mobileNumber);
}

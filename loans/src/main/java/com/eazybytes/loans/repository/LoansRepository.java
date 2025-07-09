package com.eazybytes.loans.repository;

import com.eazybytes.loans.entity.Loans;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository <Loans, Long> {


    Optional <Loans> findByMobileNumber(String mobileNumber);

    Optional <Loans> findByLoanNumber( String loanNumber);
}

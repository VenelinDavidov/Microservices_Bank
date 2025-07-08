package com.eazybytes.loans.repository;

import com.eazybytes.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository <Loans, Long> {


}

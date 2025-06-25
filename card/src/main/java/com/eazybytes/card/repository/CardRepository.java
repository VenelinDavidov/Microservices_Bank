package com.eazybytes.card.repository;

import com.eazybytes.card.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository <Cards, Long> {


}

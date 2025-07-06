package com.eazybytes.card.service.impl;

import com.eazybytes.card.constants.CardsConstants;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entity.Cards;
import com.eazybytes.card.exception.CardAlreadyExistsException;
import com.eazybytes.card.repository.CardRepository;
import com.eazybytes.card.service.ICardService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Builder
public class ICardServiceImpl implements ICardService {

    @Autowired
    private  CardRepository cardRepository;




    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> optionalCards= cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {

//        Cards newCard = new Cards();
//        long randomCardNumber = 100000000000L + new Random ().nextInt(900000000);
//
//        newCard.setCardNumber(Long.toString(randomCardNumber));
//        newCard.setMobileNumber(mobileNumber);
//        newCard.setCardType(CardsConstants.CREDIT_CARD);
//        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
//        newCard.setAmountUsed(0);
//        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
//        newCard.setCreatedAt (LocalDateTime.now ());
//        newCard.setCreatedBy ("Admin");
//
//        return newCard;
        long randomCardNumber = 100000000000L + new Random ().nextInt(900000000);

              Cards cards  = Cards.builder ()
                .mobileNumber (mobileNumber)
                .cardNumber (String.valueOf (randomCardNumber))
                .cardType (CardsConstants.CREDIT_CARD)
                .totalLimit (CardsConstants.NEW_CARD_LIMIT)
                .amountUsed (0)
                .availableAmount (CardsConstants.NEW_CARD_LIMIT)
                .build ();

              cards.setCreatedAt (LocalDateTime.now ());
              cards.setCreatedBy ("Admin");

        return cards;
    }



    @Override
    public CardsDto fetchCard(String mobileNumber) {
        return null;
    }



    @Override
    public boolean updateCard(CardsDto cardsDto) {
        return false;
    }




    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }
}

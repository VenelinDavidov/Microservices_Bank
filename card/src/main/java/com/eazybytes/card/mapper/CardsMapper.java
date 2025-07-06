package com.eazybytes.card.mapper;

import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entity.Cards;

public class CardsMapper {

    public  static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {

        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());

        return cardsDto;
    }


    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {

        cards.setCardType (cardsDto.getCardType());
        cards.setTotalLimit (cardsDto.getTotalLimit());
        cards.setAmountUsed (cardsDto.getAmountUsed());
        cards.setAvailableAmount (cardsDto.getAvailableAmount());
        cards.setMobileNumber (cardsDto.getMobileNumber());
        cards.setCardNumber (cardsDto.getCardNumber());

        return cards;
    }
}

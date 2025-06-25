package com.eazybytes.card.service.impl;

import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.service.ICardService;

public class ICardServiceImpl implements ICardService {

    @Override
    public void createCard(String mobileNumber) {

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

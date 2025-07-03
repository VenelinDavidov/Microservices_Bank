package com.eazybytes.card.service.impl;

import com.eazybytes.card.dto.CardsRequest;
import com.eazybytes.card.service.ICardService;

public class ICardServiceImpl implements ICardService {

    @Override
    public void createCard(String mobileNumber) {

    }

    @Override
    public CardsRequest fetchCard(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateCard(CardsRequest cardsDto) {
        return false;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }
}

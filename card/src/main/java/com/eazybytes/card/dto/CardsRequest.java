package com.eazybytes.card.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsRequest {

    @NotEmpty(message = "Mobile number should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Card number should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card number should be of 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Card type should not be null or empty")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than 0")
    private int amountUsed;

    @PositiveOrZero(message = "Available amount should be equal or greater than 0")
    private int availableAmount;
}

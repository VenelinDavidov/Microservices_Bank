package com.eazybytes.card.controller;

import com.eazybytes.card.constants.CardsConstants;
import com.eazybytes.card.dto.ResponseDto;
import com.eazybytes.card.service.ICardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {

    @Autowired
    private ICardService iCardService;;


    @Tag(
            name = "CRUD REST APIs for Cards in EazyBank",
            description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(    @Valid
                                                      @RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                       String mobileNumber) {
        iCardService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto (CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }


}

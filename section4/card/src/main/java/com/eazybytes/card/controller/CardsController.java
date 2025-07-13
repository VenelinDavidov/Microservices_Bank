package com.eazybytes.card.controller;

import com.eazybytes.card.constants.CardsConstants;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.dto.ErrorResponseDto;
import com.eazybytes.card.dto.ResponseDto;
import com.eazybytes.card.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {

    @Autowired
    private ICardService iCardService;;


    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    // create card on mobile number
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


  @Operation(
          summary = "Fetch Card Details REST API",
          description = "REST API to fetch Card details from EazyBank"
  )
  @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
           @ApiResponse(
                   responseCode = "500",
                   description = "HTTP Status Internal Server Error",
                   content = @Content(
                           schema = @Schema(implementation = ErrorResponseDto.class)
                   )
          )
  })

   // fetch card details on mobile number
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                     @Pattern (regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
                                                     String mobileNumber) {

        CardsDto cards = iCardService.fetchCard (mobileNumber);

        return ResponseEntity
                .status (HttpStatus.OK)
                .body (cards);
    }




    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update Card details based on a card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

  // update card details
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails (    @Valid
                                                              @RequestBody  // read json from Put/Post request and map to java object
                                                              CardsDto cardsDto) {

        boolean updateCard = iCardService.updateCard (cardsDto);


        if (updateCard) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseDto (CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status (HttpStatus.EXPECTATION_FAILED)
                    .body (new ResponseDto (CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }



    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

  // delete card details
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(    @RequestParam
                                                             @Pattern (regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
                                                             String mobileNumber) {

        boolean deleteCard = iCardService.deleteCard (mobileNumber);

        if (deleteCard) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseDto (CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status (HttpStatus.EXPECTATION_FAILED)
                    .body (new ResponseDto (CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }

    }
}

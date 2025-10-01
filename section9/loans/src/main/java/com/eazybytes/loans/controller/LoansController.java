package com.eazybytes.loans.controller;


import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.ErrorResponseDto;
import com.eazybytes.loans.dto.LoansContactInfoDto;
import com.eazybytes.loans.dto.LoansRequest;
import com.eazybytes.loans.dto.ResponseLoansDto;
import com.eazybytes.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(
        name = "CRUD REST APIs for Loans",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);
    private final ILoansService loansService;



    public LoansController(ILoansService loansService) {
        this.loansService = loansService;
    }




    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private LoansContactInfoDto loansContactInfoDto;





    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
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
    @PostMapping("/create")
    public ResponseEntity<ResponseLoansDto> createLoans (
                                                       @Valid
                                                       @Pattern (regexp = "^[0-9]{10}$", message = "Mobile number should be of 10 digits")
                                                       @RequestParam String mobileNumber) {

        loansService.createLoans(mobileNumber);

        return ResponseEntity
                .status (HttpStatus.CREATED)
                .body (new ResponseLoansDto (LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));

    }




     @Operation(
             summary = "Fetch Loan REST API",
             description = "REST API to fetch loan details based on a mobile number"
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
    @GetMapping("/fetch")
    public ResponseEntity<LoansRequest> fetchLoansDetails ( @RequestHeader("eazybank-correlation-id") String correlationId,
                                                            @RequestParam
                                                            @Pattern (regexp = "^[0-9]{10}$", message = "Mobile number should be of 10 digits")
                                                            String mobileNumber) {

        logger.debug("EazyBank-correlation-id found:{}", correlationId);
        LoansRequest loansRequest = loansService.fetchLoans (mobileNumber);

        return ResponseEntity
                .status (HttpStatus.OK)
                .body (loansRequest);
    }




    @Operation(
            summary = "Update Loan REST API",
            description = "REST API to update loan details based on a loan number"
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
    @PutMapping("/update")
    public ResponseEntity<ResponseLoansDto> updateLoansDetails (    @Valid
                                                                    @RequestBody
                                                                    LoansRequest loansDto) {

        boolean isUpdated = loansService.updateLoans (loansDto);

        if (isUpdated) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseLoansDto (LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status (HttpStatus.EXPECTATION_FAILED)
                    .body (new ResponseLoansDto (LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }

    }



    @Operation(
            summary = "Delete Loan REST API",
            description = "REST API to delete Loan details based on a mobile number"
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
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseLoansDto> deleteLoansDetails (    @RequestParam
                                                                    @Pattern (regexp = "^[0-9]{10}$", message = "Mobile number should be of 10 digits")
                                                                    String mobileNumber) {
        boolean isDeleted = loansService.deleteLoans (mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseLoansDto (LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status (HttpStatus.EXPECTATION_FAILED)
                    .body (new ResponseLoansDto (LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }











    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into loans microservice"
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
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
           return ResponseEntity
                   .status (HttpStatus.OK)
                   .body (buildVersion);
    }





    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into loans microservice"
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
    }
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {

        return ResponseEntity
                .status (HttpStatus.OK)
                .body (environment.getProperty ("JAVA_HOME"));
    }





    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
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
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status (HttpStatus.OK)
                .body (loansContactInfoDto);
    }
}

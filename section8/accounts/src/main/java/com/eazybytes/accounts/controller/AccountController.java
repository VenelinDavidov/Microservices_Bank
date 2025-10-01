
package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.ErrorResponseRequest;
import com.eazybytes.accounts.dto.ResponseRequest;
import com.eazybytes.accounts.service.IAccountsService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Rest API for Accounts in EazyBank",
        description = "CRUD Rest API for Accounts in EazyBank, Crate, Update, Fetch, Delete account details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {


    private final IAccountsService iAccountsService;



    public AccountController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }




    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;



    @Operation(
            summary = "Create Account in EazyBank",
            description = "Rest API to create new Customer & Account inside EazyBank"
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
                            schema = @Schema(implementation = ErrorResponseRequest.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity <ResponseRequest> createAccount(@Valid @RequestBody CustomerRequest customerDto) {

        iAccountsService.createAccount (customerDto);

        return ResponseEntity
                .status (HttpStatus.CREATED)
                .body (new ResponseRequest (AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }



     @Operation(
             summary = "Update Account details in Rest API",
             description = "Rest API to update Customer & Account details based on mobile number"
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
                             schema = @Schema(implementation = ErrorResponseRequest.class)
                     )
             )
       }
     )

    @PutMapping("/update")
    public ResponseEntity <ResponseRequest> updateAccountDetails(@Valid @RequestBody CustomerRequest customerDto) {

        boolean isUpdate = iAccountsService.updateAccount (customerDto);
        if (isUpdate) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseRequest (AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {

            return ResponseEntity
                    .status (HttpStatus.EXPECTATION_FAILED)
                    .body (new ResponseRequest (AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }

    }



    @Operation(
            summary = "Fetch Account details in Rest API",
            description = "Rest API to fetch Customer & Account details based on mobile number"
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
                            schema = @Schema(implementation = ErrorResponseRequest.class)
                    )
            )
      }
    )
    @GetMapping("/fetch")
    public ResponseEntity <CustomerRequest> fetchAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
                                                                String mobileNumber) {
        CustomerRequest customerRequest = iAccountsService.fetchAccountDetails (mobileNumber);

        return ResponseEntity
                .status (HttpStatus.OK)
                .body (customerRequest);
    }



    @Operation(
            summary = "Delete Account & Customer details in Rest API",
            description = "Rest API to delete Customer & Account details based on mobile number"
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
                            schema = @Schema(implementation = ErrorResponseRequest.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity <ResponseRequest> deleteAccountDetails(@RequestParam
                                                                 @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits")
                                                                 String mobileNumber) {

        boolean isDeleted = iAccountsService.deleteAccount (mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status (HttpStatus.OK)
                    .body (new ResponseRequest (AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status (HttpStatus.EXPECTATION_FAILED)
                    .body (new ResponseRequest (AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }


  // 1 one
    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into microservice accounts"
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
                            schema = @Schema(implementation = ErrorResponseRequest.class)
                    )
            )
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status (HttpStatus.OK)
                .body (buildVersion);
    }



  // 2
    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into accounts microservice"
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
                            schema = @Schema(implementation = ErrorResponseRequest.class)
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
            summary = "Get Contact information",
            description = "Get Contact info  details can be reached out in case of any issues"
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
                            schema = @Schema(implementation = ErrorResponseRequest.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status (HttpStatus.OK)
                .body (accountsContactInfoDto);
    }
}

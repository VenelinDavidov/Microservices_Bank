package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
       name = "ErrorResponse", description = "Schema to hold error response information"
)
   public class    ErrorResponseRequest {

    @Schema(
            description = "API path invoke by client"
    )
    private String apiPath;



    @Schema(
            description = "Error code representing the error happened"
    )
    private String errorCode;



    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;



    @Schema(
            description = "Date and time when the error happened"
    )
    private LocalDateTime errorDateTime;

}

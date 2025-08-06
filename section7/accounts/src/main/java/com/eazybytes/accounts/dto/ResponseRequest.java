package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@Schema(
       name = "Response", description = "Schema to hold response information")
public class ResponseRequest {

    @Schema(description = "Status code for response")
    private String statusCode;

    @Schema(description = "Status message for response")
    private String statusMsg;



}

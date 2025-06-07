package com.eazybytes.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseRequest {

    private String statusCode;

    private String statusMsg;

}

package com.eazybytes.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class    ErrorResponseRequest {

    private String apiPath;

    private String errorCode;

    private String errorMessage;

    private LocalDateTime errorDateTime;

}

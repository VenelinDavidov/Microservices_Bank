package com.eazybytes.accounts.exception;

import com.eazybytes.accounts.dto.ErrorResponseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler (CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseRequest> handleCustomerAlreadyExistsException(CustomerAlreadyExistException exception, WebRequest webRequest){

        ErrorResponseRequest errorResponseDTO = new ErrorResponseRequest (
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST.toString (),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}

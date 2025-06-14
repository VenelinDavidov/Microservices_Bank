package com.eazybytes.accounts.exception;

import com.eazybytes.accounts.dto.ErrorResponseRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler (CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseRequest> handleCustomerAlreadyExistsException(CustomerAlreadyExistException exception,
                                                                                     WebRequest webRequest){

        ErrorResponseRequest errorResponseDTO = new ErrorResponseRequest (
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST.toString (),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorResponseRequest> handleGlobalException(Exception exception, WebRequest webRequest) {

        ErrorResponseRequest errorResponseDTO = new ErrorResponseRequest (
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.toString (),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseRequest> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                                WebRequest webRequest) {

        ErrorResponseRequest errorResponseDTO = new ErrorResponseRequest (
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND.toString (),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> validationErrors = new HashMap<>();

        List<ObjectError> validationErrorsList = ex.getBindingResult().getAllErrors();

        validationErrorsList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMessage = error.getDefaultMessage();

            validationErrors.put(fieldName, validationMessage);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}

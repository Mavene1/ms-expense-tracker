package com.mavene.expensetracker.utils;

import com.mavene.expensetracker.dto.ResponseDto;
import com.mavene.expensetracker.exception.EtAuthException;
import com.mavene.expensetracker.exception.EtBadRequestException;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EtResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(EtResourceNotFoundException ex) {
        return buildErrorResponse(
                "Failed, Not found",
                404,
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EtBadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(EtBadRequestException ex) {
        return buildErrorResponse(
                "Failed, Bad request",
                400,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(EtAuthException.class)
    public ResponseEntity<Object> handleAuthException(EtAuthException ex) {
        return buildErrorResponse(
                "Forbidden",
                403,
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return buildErrorResponse(
                "Failed",
                500,
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private ResponseEntity<Object> buildErrorResponse(  String responseMessage, int responseCode,String customerMessage, HttpStatus status) {
        ResponseDto<Object> response = ApiResponse.createErrorResponse( responseCode, responseMessage, customerMessage);
        return new ResponseEntity<>(response, status);
    }
}


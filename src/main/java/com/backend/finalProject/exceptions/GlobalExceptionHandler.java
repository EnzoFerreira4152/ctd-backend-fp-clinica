package com.backend.finalProject.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> processAnyException(Exception ex, WebRequest req){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error has ocurred: " + ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> processResourceNotFound(ResourceNotFoundException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested resource was not found: " + ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> processBadRequest(BadRequestException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The request could not be processed: " + ex.getMessage());
    }

}

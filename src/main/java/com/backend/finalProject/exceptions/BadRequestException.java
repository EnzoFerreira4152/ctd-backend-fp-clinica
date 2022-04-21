package com.backend.finalProject.exceptions;


public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}

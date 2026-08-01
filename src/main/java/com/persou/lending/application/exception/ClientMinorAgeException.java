package com.persou.lending.application.exception;

import org.springframework.http.HttpStatus;

public class ClientMinorAgeException extends RuntimeException {

    private final HttpStatus status;

    public ClientMinorAgeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ClientMinorAgeException(String message) {
        super(message);
        this.status = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

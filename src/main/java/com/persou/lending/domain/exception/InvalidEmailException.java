package com.persou.lending.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends ApiException {
    public InvalidEmailException(String message) {
        super(message,HttpStatus.BAD_REQUEST);
    }
}
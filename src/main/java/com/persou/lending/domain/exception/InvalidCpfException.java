package com.persou.lending.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidCpfException extends ApiException{
    public InvalidCpfException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

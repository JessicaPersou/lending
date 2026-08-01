package com.persou.lending.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidClient extends ApiException {
    public InvalidClient(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}

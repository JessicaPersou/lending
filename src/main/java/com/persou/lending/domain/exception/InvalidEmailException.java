package com.persou.lending.domain.exception;

import com.persou.lending.adapter.in.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends ApiException {
    public InvalidEmailException(String message) {
        super(message,HttpStatus.BAD_REQUEST);
    }
}
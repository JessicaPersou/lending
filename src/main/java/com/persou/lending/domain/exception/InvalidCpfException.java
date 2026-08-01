package com.persou.lending.domain.exception;

import com.persou.lending.adapter.in.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidCpfException extends ApiException{
    public InvalidCpfException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

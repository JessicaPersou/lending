package com.persou.lending.domain.exception;

import com.persou.lending.adapter.in.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidBirthdateException extends ApiException {
    public InvalidBirthdateException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}

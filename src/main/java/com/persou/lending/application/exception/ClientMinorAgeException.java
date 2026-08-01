package com.persou.lending.application.exception;

import com.persou.lending.adapter.in.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ClientMinorAgeException extends ApiException {

    public ClientMinorAgeException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

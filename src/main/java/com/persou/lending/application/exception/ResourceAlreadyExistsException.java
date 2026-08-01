package com.persou.lending.application.exception;

import com.persou.lending.adapter.in.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends ApiException {

    public ResourceAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}

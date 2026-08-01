package com.persou.lending.application.exception;

import com.persou.lending.domain.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends ApiException {

    public ResourceAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}

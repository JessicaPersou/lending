package com.persou.lending.domain.exception;

public class InvalidClient extends RuntimeException {
    public InvalidClient(String message) {
        super(message);
    }
}

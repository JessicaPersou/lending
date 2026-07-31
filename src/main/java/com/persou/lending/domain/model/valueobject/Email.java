package com.persou.lending.domain.model.valueobject;

import com.persou.lending.domain.exception.InvalidEmailException;

public record Email(String value) {
    public Email {
        if (value == null || !value.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidEmailException("E-mail inválido: " + value);
        }
        value = value.trim().toLowerCase();
    }
}

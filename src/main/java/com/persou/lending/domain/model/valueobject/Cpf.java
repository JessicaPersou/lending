package com.persou.lending.domain.model.valueobject;

import com.persou.lending.domain.exception.InvalidCpfException;

public record Cpf(String value) {
    public Cpf {
        if (value == null || !value.matches("^[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-[0-9]{2}$")) {
            throw new InvalidCpfException("CPF inválido: " + value);
        }
        value = value.trim();
    }
}

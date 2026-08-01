package com.persou.lending.domain.model.valueobject;

import com.persou.lending.domain.exception.InvalidBirthdateException;
import java.time.LocalDate;
import java.time.Period;

public record Birthdate(LocalDate value) {

    public Birthdate {
        if (value == null || value.isAfter(LocalDate.now())) {
            throw new InvalidBirthdateException("Data inválida: " + value);
        }
    }

    public int ageAt(LocalDate reference) {
        return Period.between(value, reference).getYears();
    }
}

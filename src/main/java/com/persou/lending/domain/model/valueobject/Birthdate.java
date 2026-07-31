package com.persou.lending.domain.model.valueobject;

import com.persou.lending.domain.exception.InvalidBirthdateException;
import java.time.LocalDate;
import java.time.Period;

public record Birthdate(LocalDate value) {

    public Birthdate(LocalDate value) {
        if (value == null || value.isAfter(LocalDate.now())) {
            throw new InvalidBirthdateException("Data inválida: " + value);
        }

        Period period = Period.between(value, LocalDate.now());
        if (period.getYears() < 18) {
            throw new InvalidBirthdateException("Menor de idade: " + value);
        }
        this.value = value;
    }

    public LocalDate today() {
        return LocalDate.now();
    }

    public LocalDate ageOfMajority() {
        return LocalDate.now().minusYears(18);
    }

    public Period period() {
        return Period.between(value, LocalDate.now());
    }
}

package com.persou.lending.adapter.in.api.dto;

import java.time.LocalDate;

public record ClientDTO(
    String name,
    String document,
    String email,
    LocalDate birthdate
) {
}

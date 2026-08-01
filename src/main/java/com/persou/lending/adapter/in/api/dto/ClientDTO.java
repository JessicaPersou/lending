package com.persou.lending.adapter.in.api.dto;

import java.time.LocalDate;

public record ClientDTO(
    String name,
    String cpf,
    String email,
    LocalDate birthdate
) {
}

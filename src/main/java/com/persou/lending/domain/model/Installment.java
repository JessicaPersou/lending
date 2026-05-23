package com.persou.lending.domain.model;

import com.persou.lending.domain.model.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public record Installment(
    Long id,
    int installment,
    BigDecimal value,
    LocalDate dueDate,
    PaymentStatus status,
    Contract contract
) {
}
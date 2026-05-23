package com.persou.lending.domain.model;

import java.math.BigDecimal;

public record InterestRate(
    Long id,
    int minInstallment,
    int maxInstallment,
    BigDecimal rateIncrement,
    BigDecimal iofFixedRate
) {
}

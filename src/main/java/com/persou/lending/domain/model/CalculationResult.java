package com.persou.lending.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CalculationResult(
    Long id,
    BigDecimal appliedBaseRate,
    BigDecimal incrementRate,
    BigDecimal totalIOfAmount,
    BigDecimal totalInterestAmount,
    BigDecimal totalPayable,
    LocalDateTime calculationDate,
    LocalDateTime expiresAt,
    Proposal proposal
) {
}

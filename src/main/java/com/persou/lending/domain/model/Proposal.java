package com.persou.lending.domain.model;

import com.persou.lending.domain.model.enums.StatusAnalisis;
import java.math.BigDecimal;

public record Proposal(
    Long id,
    BigDecimal requestValue,
    Integer installmentsQty,
    BigDecimal monthlyIncome,
    StatusAnalisis statusAnalisis
) {
}
package com.persou.lending.domain.model;

import com.persou.lending.domain.model.enums.StatusAnalisis;
import java.math.BigDecimal;

public record Proposal(
    Long id,
    BigDecimal requestValue,
    BigDecimal installmentsQty,
    BigDecimal monthlyIncome,
    StatusAnalisis statusAnalisis,
    Client client
) {
}
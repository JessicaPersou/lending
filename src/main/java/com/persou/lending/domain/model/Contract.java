package com.persou.lending.domain.model;

public record Contract(
    Long id,
    java.time.LocalDateTime signedAt,
    java.math.BigDecimal totalAmount,
    Integer installmentsCount,
    com.persou.lending.domain.model.enums.ContractStatus status,
    Proposal proposal,
    Client client
) {
}
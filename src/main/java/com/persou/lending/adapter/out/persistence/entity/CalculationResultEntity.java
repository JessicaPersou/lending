package com.persou.lending.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CALCULATION_RESULT")
@AllArgsConstructor
@NoArgsConstructor
public class CalculationResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "APPLIED_BASE_RATE")
    private BigDecimal appliedBaseRate;
    @Column(name = "INCREMENT_RATE")
    private BigDecimal incrementRate;
    @Column(name = "TOTAL_IOF_AMOUNT")
    private BigDecimal totalIOfAmount;
    @Column(name = "TOTAL_INTEREST_AMOUNT")
    private BigDecimal totalInterestAmount;
    @Column(name = "TOTAL_PAYABLE")
    private BigDecimal totalPayable;
    @Column(name = "CALCULATION_DATE")
    private LocalDateTime calculationDate;
    @Column(name = "EXPIRES_AT")
    private LocalDateTime expiresAt;
    @OneToOne(mappedBy = "calculationResult", fetch = FetchType.LAZY)
    private ProposalEntity proposal;
}

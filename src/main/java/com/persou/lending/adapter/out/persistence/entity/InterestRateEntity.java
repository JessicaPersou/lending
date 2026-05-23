package com.persou.lending.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "INTEREST_RATE")
@AllArgsConstructor
@NoArgsConstructor
public class InterestRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "MIN_INSTALLMENT")
    private int minInstallment;
    @Column(name = "MAX_INSTALLMENT")
    private int maxInstallment;
    @Column(name = "RATE_INCREMENT")
    private BigDecimal rateIncrement;
    @Column(name = "IOF_FIXED_RATE")
    private BigDecimal iofFixedRate;
}

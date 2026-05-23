package com.persou.lending.adapter.out.persistence.entity;

import com.persou.lending.domain.model.enums.StatusAnalisis;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PROPOSAL")
@AllArgsConstructor
@NoArgsConstructor
public class ProposalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "REQUEST_VALUE")
    private BigDecimal requestValue;
    @Column(name = "INSTALLMENTS_QTY")
    private BigDecimal installmentsQty;
    @Column(name = "MONTHLY_INCOME")
    private BigDecimal monthlyIncome;
    @Column(name = "STATUS_ANALISIS")
    @Enumerated(EnumType.STRING)
    private StatusAnalisis statusAnalisis;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity client;
    @OneToOne(mappedBy = "proposal", cascade = CascadeType.ALL)
    private ContractEntity contract;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CALC_RESULT_ID")
    private CalculationResultEntity calculationResult;
}

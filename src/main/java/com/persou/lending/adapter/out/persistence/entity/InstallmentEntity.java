package com.persou.lending.adapter.out.persistence.entity;
import com.persou.lending.domain.model.enums.PaymentStatus;
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
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "INSTALLMENT")
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "INSTALLMENT")
    private int installment;
    @Column(name = "VALUE")
    private BigDecimal value;
    @Column(name = "DUE_DATE")
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PaymentStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", nullable = false)
    private ContractEntity contract;
}

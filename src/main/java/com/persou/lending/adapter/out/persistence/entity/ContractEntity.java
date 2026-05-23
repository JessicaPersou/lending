package com.persou.lending.adapter.out.persistence.entity;

import com.persou.lending.domain.model.enums.ContractStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONTRACT")
@AllArgsConstructor
@NoArgsConstructor
public class ContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SIGNED_AT", columnDefinition = "TIMESTAMP")
    private LocalDateTime signedAt;
    @Column(name = "TOTAL_AMOUNT", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;
    @Column(name = "INSTALLMENTS_COUNT", nullable = false)
    private Integer installmentsCount;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private ContractStatus status;
    @OneToOne
    @JoinColumn(name = "PROPOSAL_ID", nullable = false)
    private ProposalEntity proposal;
    @OneToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity client;
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstallmentEntity> installments;
}

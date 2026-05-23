package com.persou.lending.adapter.out.persistence.entity;

import com.persou.lending.domain.model.enums.ProfileState;
import com.persou.lending.domain.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENT")
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DOCUMENT")
    private String document;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;
    @Column(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "PROFILE_STATE")
    @Enumerated(EnumType.STRING)
    private ProfileState profileState;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProposalEntity> proposals;
    @OneToOne(mappedBy = "client")
    @CollectionTable
    private ContractEntity contract;
}


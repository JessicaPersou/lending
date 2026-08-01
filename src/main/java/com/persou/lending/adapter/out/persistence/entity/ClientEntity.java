package com.persou.lending.adapter.out.persistence.entity;

import com.persou.lending.domain.model.enums.ProfileState;
import com.persou.lending.domain.model.enums.UserRole;
import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "CLIENT")
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
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private ContractEntity contract;

    public ClientEntity() {
    }

    public ClientEntity(Long id, String name, String document, String email, LocalDate birthdate, UserRole userRole,
                        ProfileState profileState, List<ProposalEntity> proposals, ContractEntity contract) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.birthdate = birthdate;
        this.userRole = userRole;
        this.profileState = profileState;
        this.proposals = proposals;
        this.contract = contract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public ProfileState getProfileState() {
        return profileState;
    }

    public void setProfileState(ProfileState profileState) {
        this.profileState = profileState;
    }

    public List<ProposalEntity> getProposals() {
        return proposals;
    }

    public void setProposals(List<ProposalEntity> proposals) {
        this.proposals = proposals;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
}


package com.persou.lending.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNT_ACTIVATION_TOKEN")
@AllArgsConstructor
@NoArgsConstructor
public class AccountActivationTokenEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "EXPIRES_AT")
    private Instant expiresAt;
    @Column(name = "USED")
    private boolean used;
    @Column(name = "CLIENT_ID")
    private Long clientId;
}

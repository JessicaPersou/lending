package com.persou.lending.adapter.out.persistence;

import com.persou.lending.adapter.out.persistence.entity.AccountActivationTokenEntity;
import com.persou.lending.adapter.out.persistence.repository.AccountActivationTokenJpaRepository;
import com.persou.lending.application.mapper.AccountActivationTokenMapper;
import com.persou.lending.domain.model.AccountActivationToken;
import com.persou.lending.domain.port.out.persistence.AccountActivationTokenPortOut;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountActivationTokenPersistenceAdapterOut implements AccountActivationTokenPortOut {

    private final AccountActivationTokenJpaRepository jpaRepository;
    private final AccountActivationTokenMapper mapper;

    @Override
    public AccountActivationToken generateTokenAndSave(Long clientId) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(2, ChronoUnit.HOURS);
        AccountActivationToken token = new AccountActivationToken(clientId, generateTokenSecurityRandom(), expiresAt, false );
        AccountActivationTokenEntity savedToken = jpaRepository.save(mapper.toEntity(token));
        return mapper.toDomain(savedToken);
    }

    private String generateTokenSecurityRandom(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}

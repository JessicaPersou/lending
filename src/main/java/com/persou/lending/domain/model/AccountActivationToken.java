package com.persou.lending.domain.model;

import java.time.Instant;

public record AccountActivationToken(
    Long idClient,
    String token,
    Instant expiresAt,
    boolean used
) {
    public boolean isValid() {
        return !used && Instant.now().isBefore(expiresAt);
    }

}

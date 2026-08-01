package com.persou.lending.domain.port.out.persistence;

import com.persou.lending.domain.model.AccountActivationToken;

public interface AccountActivationTokenPortOut {
    AccountActivationToken generateTokenAndSave(Long clientId);
}

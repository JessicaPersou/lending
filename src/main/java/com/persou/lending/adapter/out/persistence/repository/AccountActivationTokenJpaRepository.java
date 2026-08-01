package com.persou.lending.adapter.out.persistence.repository;

import com.persou.lending.adapter.out.persistence.entity.AccountActivationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountActivationTokenJpaRepository extends JpaRepository<AccountActivationTokenEntity, Long> {


}

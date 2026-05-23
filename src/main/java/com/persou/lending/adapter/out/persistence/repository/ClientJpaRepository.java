package com.persou.lending.adapter.out.persistence.repository;

import com.persou.lending.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientEntity,Long> {
}

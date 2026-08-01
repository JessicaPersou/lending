package com.persou.lending.adapter.out.persistence.repository;

import com.persou.lending.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END
            FROM ClientEntity c
            WHERE c.email = :email OR c.document = :cpf
        """)
    boolean existsByEmailOrCpf(String email, String cpf);
}

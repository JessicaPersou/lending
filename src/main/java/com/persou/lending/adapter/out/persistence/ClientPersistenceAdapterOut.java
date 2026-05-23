package com.persou.lending.adapter.out.persistence;

import com.persou.lending.adapter.out.persistence.repository.ClientJpaRepository;
import com.persou.lending.application.mapper.ClientMapper;
import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.port.out.persistence.ClientPersistencePortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClientPersistenceAdapterOut implements ClientPersistencePortOut {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;

    @Override
    public Client save(Client client) {
        var entity = clientMapper.toEntity(client);
        var saved = clientJpaRepository.save(entity);
        return clientMapper.toDomain(saved);
    }
}

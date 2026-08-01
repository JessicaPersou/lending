package com.persou.lending.domain.port.out.persistence;

import com.persou.lending.domain.model.Client;

public interface ClientPersistencePortOut {

    boolean existsByEmailOrCpf(String email, String cpf);

    Client save(Client client);
}

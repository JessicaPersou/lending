package com.persou.lending.domain.port.out.persistence;

import com.persou.lending.domain.model.Client;

public interface ClientPersistencePortOut {

    Client save(Client client);
}

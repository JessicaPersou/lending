package com.persou.lending.application.usecase;

import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.port.out.persistence.ClientPersistencePortOut;
import org.springframework.stereotype.Service;

@Service
public class CreateClienteUseCase {

    private final ClientPersistencePortOut clientPersistencePortOut;

    public CreateClienteUseCase(ClientPersistencePortOut clientPersistencePortOut) {
        this.clientPersistencePortOut = clientPersistencePortOut;
    }

    public Client createClient(Client client){
        return clientPersistencePortOut.save(client);
    }

}

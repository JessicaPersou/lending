package com.persou.lending.application.usecase;

import com.persou.lending.application.exception.ClientMinorAgeException;
import com.persou.lending.application.exception.ResourceAlreadyExistsException;
import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.model.valueobject.Cpf;
import com.persou.lending.domain.model.valueobject.Email;
import com.persou.lending.domain.port.out.persistence.ClientPersistencePortOut;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class CreateClienteUseCase {

    private final ClientPersistencePortOut clientPersistencePortOut;

    public CreateClienteUseCase(ClientPersistencePortOut clientPersistencePortOut) {
        this.clientPersistencePortOut = clientPersistencePortOut;
    }

    @Transactional
    public Client createClient(Client client) {
        Email email = client.email();
        Cpf cpf = client.cpf();
        if (clientPersistencePortOut.existsByEmailOrCpf(email.value(), cpf.value())) {
            throw new ResourceAlreadyExistsException("Cliente já cadastrado");
        }
        if (client.birthdate().ageAt(LocalDate.now()) < 18) {
            throw new ClientMinorAgeException("Cliente menor de idade, não pode ser cadastrado");
        }
        return clientPersistencePortOut.save(client);
    }

}

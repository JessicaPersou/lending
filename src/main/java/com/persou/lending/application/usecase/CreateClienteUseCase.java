package com.persou.lending.application.usecase;

import com.persou.lending.application.exception.ClientMinorAgeException;
import com.persou.lending.application.exception.ResourceAlreadyExistsException;
import com.persou.lending.domain.event.ActivationClientEvent;
import com.persou.lending.domain.model.AccountActivationToken;
import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.model.enums.ProfileState;
import com.persou.lending.domain.port.out.event.EventPublisherPort;
import com.persou.lending.domain.port.out.persistence.AccountActivationTokenPortOut;
import com.persou.lending.domain.port.out.persistence.ClientPersistencePortOut;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class CreateClienteUseCase {
    private static final Integer OF_LEGAL_AGE = 18;
    private final ClientPersistencePortOut clientPersistencePortOut;
    private final AccountActivationTokenPortOut accountActivationTokenPortOut;
    private final EventPublisherPort eventPublisherPort;

    public CreateClienteUseCase(ClientPersistencePortOut clientPersistencePortOut,
                                AccountActivationTokenPortOut accountActivationTokenPortOut,
                                EventPublisherPort eventPublisherPort) {
        this.clientPersistencePortOut = clientPersistencePortOut;
        this.accountActivationTokenPortOut = accountActivationTokenPortOut;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Transactional
    public Client createClient(Client client) {
        validateClientDoesNotExist(client);
        validateLegalAge(client);

        Client clientReadyToSave = client.withProfileState(
            ProfileState.PENDING_VALIDATION
        );

        Client saved = clientPersistencePortOut.save(clientReadyToSave);
        ActivationClientEvent generatedToken = generateToken(saved);
        eventPublisherPort.publish(generatedToken);
        return saved;
    }

    private void validateClientDoesNotExist(Client client) {
        if (clientPersistencePortOut.existsByEmailOrCpf(client.email().value(), client.cpf().value())) {
            throw new ResourceAlreadyExistsException("Cliente já cadastrado");
        }
    }

    private void validateLegalAge(Client client) {
        if (client.birthdate().ageAt(LocalDate.now()) < OF_LEGAL_AGE) {
            throw new ClientMinorAgeException("Cliente menor de idade, não pode ser cadastrado");
        }
    }

    private ActivationClientEvent generateToken(Client client) {
        AccountActivationToken token = accountActivationTokenPortOut.generateTokenAndSave(client.id());
        return new ActivationClientEvent(client.id(), token.token(), client.email().value());
    }
}

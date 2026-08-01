package com.persou.lending.application.usecase;

import com.persou.lending.application.exception.ClientMinorAgeException;
import com.persou.lending.application.exception.ResourceAlreadyExistsException;
import com.persou.lending.domain.model.AccountActivationToken;
import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.model.enums.ProfileState;
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

    public CreateClienteUseCase(ClientPersistencePortOut clientPersistencePortOut,
                                AccountActivationTokenPortOut accountActivationTokenPortOut) {
        this.clientPersistencePortOut = clientPersistencePortOut;
        this.accountActivationTokenPortOut = accountActivationTokenPortOut;
    }

    @Transactional
    public Client createClient(Client client) {
        validateClientDoesNotExist(client);
        validateLegalAge(client);

        Client clientReadyToSave = client.withProfileState(
            ProfileState.PENDING_VALIDATION
        );

        Client saved = clientPersistencePortOut.save(clientReadyToSave);
        AccountActivationToken generatedToken = generateToken(saved.id());
        //aqui deve chamar o metodo que publica o evento para desparar o email, passando email, token e id do client
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

    private AccountActivationToken generateToken(Long clientId) {
        return accountActivationTokenPortOut.generateTokenAndSave(clientId);
    }
}

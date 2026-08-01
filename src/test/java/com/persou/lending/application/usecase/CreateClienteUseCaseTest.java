package com.persou.lending.application.usecase;

import static com.persou.lending.domain.model.enums.ProfileState.ACTIVE;
import static com.persou.lending.domain.model.enums.StatusAnalisis.PENDING;
import static com.persou.lending.domain.model.enums.UserRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.persou.lending.application.exception.ClientMinorAgeException;
import com.persou.lending.application.exception.ResourceAlreadyExistsException;
import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.model.Proposal;
import com.persou.lending.domain.model.valueobject.Birthdate;
import com.persou.lending.domain.model.valueobject.Cpf;
import com.persou.lending.domain.model.valueobject.Email;
import com.persou.lending.domain.port.out.persistence.ClientPersistencePortOut;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateClienteUseCaseTest {

    @Mock
    private ClientPersistencePortOut clientPersistencePortOut;

    @InjectMocks
    private CreateClienteUseCase createClienteUseCase;

    @Test
    void createClientShouldDelegateToPersistencePort() {
        Client client = buildClient();

        when(clientPersistencePortOut.existsByEmailOrCpf(client.email().value(), client.cpf().value())).thenReturn(false);
        when(clientPersistencePortOut.save(client)).thenReturn(client);

        Client result = createClienteUseCase.createClient(client);

        assertEquals(client, result);
        verify(clientPersistencePortOut).save(client);
    }

    @Test
    void createClientShouldThrowExceptionWhenClientAlreadyExists() {
        Client client = buildClient();

        when(clientPersistencePortOut.existsByEmailOrCpf(client.email().value(), client.cpf().value())).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> createClienteUseCase.createClient(client));
        verifyNoMoreInteractions(clientPersistencePortOut);
    }

    @Test
    void createClientShouldPropagateExceptionWhenSaveFails() {
        Client client = buildClient();

        when(clientPersistencePortOut.existsByEmailOrCpf(client.email().value(), client.cpf().value())).thenReturn(false);
        when(clientPersistencePortOut.save(client)).thenThrow(new RuntimeException("Save failed"));

        assertThrows(RuntimeException.class, () -> createClienteUseCase.createClient(client));
        verify(clientPersistencePortOut).save(client);
        verifyNoMoreInteractions(clientPersistencePortOut);
    }

    @Test
    void createClientShouldThrowExceptionWhenClientIsMinor() {
        Client client = buildClientWithBirthdate(LocalDate.now().minusYears(15));

        when(clientPersistencePortOut.existsByEmailOrCpf(any(), any())).thenReturn(false);

        assertThrows(ClientMinorAgeException.class, () -> createClienteUseCase.createClient(client));
        verify(clientPersistencePortOut, never()).save(any());
    }

    private static Client buildClient() {
        Proposal proposal = new Proposal(
            1L,
            new BigDecimal("1200.00"),
            12,
            new BigDecimal("120.00"),
            PENDING
        );

        return new Client(
            1L,
            "Joao",
            new Cpf("123.345.567-78"),
            new Email("emaildojoao@email.com"),
            new Birthdate(LocalDate.of(1993, Month.OCTOBER, 10)),
            USER,
            ACTIVE,
            "123",
            List.of(proposal)
        );
    }

    private static Client buildClientWithBirthdate(LocalDate birthdate) {
        Proposal proposal = new Proposal(
            1L,
            new BigDecimal("1200.00"),
            12,
            new BigDecimal("120.00"),
            PENDING
        );

        return new Client(
            1L,
                "Joao",
                new Cpf("123.345.567-78"),
            new Email("emaildojoao@email.com"),
            new Birthdate(birthdate),
            USER,
            ACTIVE,
            "123",
            List.of(proposal)
        );
    }
}

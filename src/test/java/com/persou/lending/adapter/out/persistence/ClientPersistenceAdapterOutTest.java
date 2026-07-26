package com.persou.lending.adapter.out.persistence;

import static com.persou.lending.domain.model.enums.ProfileState.ACTIVE;
import static com.persou.lending.domain.model.enums.StatusAnalisis.PENDING;
import static com.persou.lending.domain.model.enums.UserRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.persou.lending.adapter.out.persistence.entity.ClientEntity;
import com.persou.lending.adapter.out.persistence.repository.ClientJpaRepository;
import com.persou.lending.application.mapper.ClientMapper;
import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.model.Proposal;
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
class ClientPersistenceAdapterOutTest {

    @Mock
    private ClientJpaRepository clientJpaRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientPersistenceAdapterOut clientPersistenceAdapterOut;

    @Test
    void saveShouldReturnClientWhenEntityIsSavedSuccessfully() {
        Client client = buildClient();
        ClientEntity entity = new ClientEntity();
        ClientEntity savedEntity = new ClientEntity();

        when(clientMapper.toEntity(client)).thenReturn(entity);
        when(clientJpaRepository.save(entity)).thenReturn(savedEntity);
        when(clientMapper.toDomain(savedEntity)).thenReturn(client);

        Client result = clientPersistenceAdapterOut.save(client);

        assertNotNull(result);
        assertEquals(client, result);

        var inOrder = inOrder(clientMapper, clientJpaRepository);
        inOrder.verify(clientMapper).toEntity(client);
        inOrder.verify(clientJpaRepository).save(entity);
        inOrder.verify(clientMapper).toDomain(savedEntity);

        verifyNoMoreInteractions(clientMapper, clientJpaRepository);
    }

    @Test
    void saveShouldThrowExceptionWhenMapperFails() {
        Client client = buildClient();

        when(clientMapper.toEntity(client)).thenThrow(new RuntimeException("Mapping failed"));

        assertThrows(RuntimeException.class, () -> clientPersistenceAdapterOut.save(client));

        verify(clientMapper).toEntity(client);
        verifyNoInteractions(clientJpaRepository);
        verifyNoMoreInteractions(clientMapper);
    }

    @Test
    void saveShouldThrowExceptionWhenRepositoryFails() {
        Client client = buildClient();
        ClientEntity entity = new ClientEntity();

        when(clientMapper.toEntity(client)).thenReturn(entity);
        when(clientJpaRepository.save(entity)).thenThrow(new RuntimeException("Save failed"));

        assertThrows(RuntimeException.class, () -> clientPersistenceAdapterOut.save(client));

        verify(clientMapper).toEntity(client);
        verify(clientJpaRepository).save(entity);
        verifyNoMoreInteractions(clientMapper, clientJpaRepository);
    }

    @Test
    void saveShouldThrowExceptionWhenMapperToDomainFails() {
        Client client = buildClient();
        ClientEntity entity = new ClientEntity();
        ClientEntity savedEntity = new ClientEntity();

        when(clientMapper.toEntity(client)).thenReturn(entity);
        when(clientJpaRepository.save(entity)).thenReturn(savedEntity);
        when(clientMapper.toDomain(savedEntity)).thenThrow(new RuntimeException("Domain mapping failed"));

        assertThrows(RuntimeException.class, () -> clientPersistenceAdapterOut.save(client));

        verify(clientMapper).toEntity(client);
        verify(clientJpaRepository).save(entity);
        verify(clientMapper).toDomain(savedEntity);
        verifyNoMoreInteractions(clientMapper, clientJpaRepository);
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
            "12334556778",
            "emaildojoao@email.com",
            LocalDate.of(1993, Month.OCTOBER, 10),
            USER,
            ACTIVE,
            List.of(proposal)
        );
    }
}

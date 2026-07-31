package com.persou.lending.adapter.in.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.persou.lending.adapter.in.api.dto.ClientDTO;
import com.persou.lending.adapter.in.api.mapper.CreateClientMapper;
import com.persou.lending.application.usecase.CreateClienteUseCase;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final CreateClienteUseCase createClienteUseCase;
    private final CreateClientMapper createClientMapper;

    @Autowired
    public ClientController(CreateClienteUseCase createClienteUseCase, CreateClientMapper createClientMapper) {
        this.createClienteUseCase = createClienteUseCase;
        this.createClientMapper = createClientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {

//        TODO: Adicionar logs inteligentes e também criar Controller advide para deixar as mensagens mais legivéis.
        var clientDomain = createClientMapper.toDomain(clientDTO);
        var createdClient = createClienteUseCase.createClient(clientDomain);
        var responseDTO = createClientMapper.toDto(createdClient);
        return ResponseEntity.ok(responseDTO);
    }
}

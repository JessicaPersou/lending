package com.persou.lending.adapter.in.api.mapper;

import com.persou.lending.adapter.in.api.dto.ClientDTO;
import com.persou.lending.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateClientMapper {

    ClientDTO toDto(Client domain);
    Client toDomain(ClientDTO dto);
}

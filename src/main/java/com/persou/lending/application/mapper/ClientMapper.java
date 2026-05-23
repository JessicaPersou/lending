package com.persou.lending.application.mapper;

import com.persou.lending.adapter.out.persistence.entity.ClientEntity;
import com.persou.lending.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientEntity toEntity(Client domain);
    Client toDomain(ClientEntity entity);
}

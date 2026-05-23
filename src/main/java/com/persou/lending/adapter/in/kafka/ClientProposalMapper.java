package com.persou.lending.adapter.in.kafka;

import com.persou.lending.adapter.in.api.dto.ClientDTO;
import com.persou.lending.avro.ClientAvro;
import com.persou.lending.domain.model.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientProposalMapper {

    Proposal toDomain(ClientAvro avro);
}

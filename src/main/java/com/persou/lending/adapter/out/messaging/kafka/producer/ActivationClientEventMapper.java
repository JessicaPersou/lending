package com.persou.lending.adapter.out.messaging.kafka.producer;

import com.persou.lending.avro.ActivationClientAvro;
import com.persou.lending.domain.event.ActivationClientEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivationClientEventMapper {
    ActivationClientAvro toAvro(ActivationClientEvent event);
}
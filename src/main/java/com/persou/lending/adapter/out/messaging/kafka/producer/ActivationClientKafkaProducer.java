package com.persou.lending.adapter.out.messaging.kafka.producer;

import com.persou.lending.avro.ActivationClientAvro;
import com.persou.lending.domain.event.ActivationClientEvent;
import com.persou.lending.domain.port.out.event.EventPublisherPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActivationClientKafkaProducer implements EventPublisherPort {

    private final KafkaTemplate<String, ActivationClientAvro> kafkaTemplate;
    private final ActivationClientEventMapper mapper;
    private final String topic;

    public ActivationClientKafkaProducer(
        KafkaTemplate<String, ActivationClientAvro> kafkaTemplate,
        ActivationClientEventMapper mapper,
        @Value("${kafka.topic.cliente-cadastrado}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
        this.topic = topic;
    }

    @Override
    public void publish(ActivationClientEvent event) {
        ActivationClientAvro avroEvent = mapper.toAvro(event);
        kafkaTemplate.send(topic, event.clientId().toString(), avroEvent);
    }
}

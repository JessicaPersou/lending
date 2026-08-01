package com.persou.lending.domain.port.out.event;

import com.persou.lending.domain.event.ActivationClientEvent;

public interface EventPublisherPort {
    void publish(ActivationClientEvent activationClientEvent);
}

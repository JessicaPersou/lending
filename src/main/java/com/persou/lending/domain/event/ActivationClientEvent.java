package com.persou.lending.domain.event;

import com.persou.lending.domain.model.valueobject.Email;

public record ActivationClientEvent(
    Long clientId,
    Email email
){
}

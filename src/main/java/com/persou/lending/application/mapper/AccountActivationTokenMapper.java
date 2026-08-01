package com.persou.lending.application.mapper;

import com.persou.lending.adapter.out.persistence.entity.AccountActivationTokenEntity;
import com.persou.lending.domain.model.AccountActivationToken;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountActivationTokenMapper {

    AccountActivationTokenEntity toEntity(AccountActivationToken token);
    AccountActivationToken toDomain(AccountActivationTokenEntity entity);
}

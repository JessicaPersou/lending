package com.persou.lending.application.mapper;

import com.persou.lending.adapter.out.persistence.entity.ClientEntity;
import com.persou.lending.domain.model.Client;
import com.persou.lending.domain.model.valueobject.Birthdate;
import com.persou.lending.domain.model.valueobject.Cpf;
import com.persou.lending.domain.model.valueobject.Email;
import java.time.LocalDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    @Mapping(target = "email", source = "email", qualifiedByName = "toEmailEntity")
    @Mapping(target = "document", source = "cpf", qualifiedByName = "toCpfEntity")
    @Mapping(target = "birthdate", source = "birthdate", qualifiedByName = "toBirthdateEntity")
    ClientEntity toEntity(Client domain);

    @Mapping(target = "email", source = "email", qualifiedByName = "toEmailDomain")
    @Mapping(target = "cpf", source = "document", qualifiedByName = "toCpfDomain")
    @Mapping(target = "birthdate", source = "birthdate", qualifiedByName = "toBirthdateDomain")
    Client toDomain(ClientEntity entity);

    @Named("toEmailEntity")
    default String toEmailEntity(Email email){
        if(email == null) return null;
        return email.value();
    }

    @Named("toEmailDomain")
    default Email toEmailDomain(String email){
        if(email == null) return null;
        return new Email(email);
    }

    @Named("toCpfEntity")
    default String toCpfEntity(Cpf cpf){
        if(cpf == null) return null;
        return cpf.value();
    }

    @Named("toCpfDomain")
    default Cpf toCpfDomain(String cpf){
        if(cpf == null) return null;
        return new Cpf(cpf);
    }

    @Named("toBirthdateEntity")
    default LocalDate toLocalDate(Birthdate birthdate){
        if(birthdate == null) return null;
        return birthdate.value();
    }
    @Named("toBirthdateDomain")
    default Birthdate toBirthdateDomain(LocalDate birthdate){
        if(birthdate == null) return null;
        return new Birthdate(birthdate);
    }
}

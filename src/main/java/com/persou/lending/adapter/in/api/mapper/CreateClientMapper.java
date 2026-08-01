package com.persou.lending.adapter.in.api.mapper;

import com.persou.lending.adapter.in.api.dto.ClientDTO;
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
public interface CreateClientMapper {

    @Mapping(target = "email", source = "email", qualifiedByName = "toEmailDto")
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "toCpfDto")
    @Mapping(target = "birthdate", source = "birthdate", qualifiedByName = "toBirthdateEntity")
    ClientDTO toDto(Client domain);

    @Mapping(target = "email", source = "email", qualifiedByName = "toEmailDomain")
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "toCpfDomain")
    @Mapping(target = "birthdate", source = "birthdate", qualifiedByName = "toBirthdateDomain")
    Client toDomain(ClientDTO dto);

    @Named("toEmailDto")
    default String toEmailDto(Email email) {
        if (email == null) {
            return null;
        }
        return email.value();
    }

    @Named("toEmailDomain")
    default Email toEmailDomain(String email) {
        if (email == null) {
            return null;
        }
        return new Email(email);
    }

    @Named("toCpfDto")
    default String toCpfDto(Cpf cpf) {
        if (cpf == null) {
            return null;
        }
        return cpf.value();
    }

    @Named("toCpfDomain")
    default Cpf toCpfDomain(String cpf) {
        if (cpf == null) {
            return null;
        }
        return new Cpf(cpf);
    }

    @Named("toBirthdateEntity")
    default LocalDate toLocalDate(Birthdate birthdate) {
        if (birthdate == null) {
            return null;
        }
        return birthdate.value();
    }

    @Named("toBirthdateDomain")
    default Birthdate toBirthdateDomain(LocalDate birthdate) {
        if (birthdate == null) {
            return null;
        }
        return new Birthdate(birthdate);
    }
}

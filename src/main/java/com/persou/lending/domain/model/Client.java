package com.persou.lending.domain.model;

import com.persou.lending.domain.model.enums.ProfileState;
import com.persou.lending.domain.model.enums.UserRole;
import com.persou.lending.domain.model.valueobject.Email;
import com.persou.lending.domain.model.valueobject.Cpf;
import com.persou.lending.domain.model.valueobject.Birthdate;
import java.util.List;

public record Client(
    Long id,
    String name,
    Cpf cpf,
    Email email,
    Birthdate birthdate,
    UserRole userRole,
    ProfileState profileState,
    List<Proposal> proposal
) {
}

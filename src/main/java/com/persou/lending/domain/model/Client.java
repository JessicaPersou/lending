package com.persou.lending.domain.model;

import com.persou.lending.domain.model.enums.ProfileState;
import com.persou.lending.domain.model.enums.UserRole;
import java.time.LocalDate;
import java.util.List;

public record Client(
    Long id,
    String name,
    String document,
    String email,
    LocalDate birthdate,
    UserRole userRole,
    ProfileState profileState,
    List<Proposal> proposal
) {
}

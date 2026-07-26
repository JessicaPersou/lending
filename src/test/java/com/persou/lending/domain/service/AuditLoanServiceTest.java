package com.persou.lending.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AuditLoanServiceTest {

    @Test
    void shouldInstantiateAuditLoanService() {
        AuditLoanService service = new AuditLoanService();

        assertNotNull(service);
    }
}


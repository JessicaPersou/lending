package com.persou.lending.domain.port.in;

import com.persou.lending.domain.model.Proposal;

public interface ValidAndRegisterClientPortIn {

    void validAndRegister(Proposal proposal);
}

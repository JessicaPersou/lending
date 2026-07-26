package com.persou.lending.adapter.in.kafka;

import com.persou.lending.avro.ClientAvro;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProposalKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProposalKafkaConsumer.class);
//    private final ValidAndRegisterClientPortIn validAndRegisterClientPortIn;
    private final ClientProposalMapper  clientProposalMapper;

    @KafkaListener(topics = "propostas", groupId = "lending-group")
    public void consumeProposal(ClientAvro proposal) {
        log.info("Recebendo nova proposta de empréstimo: {}", proposal);
        log.info("Cliente: {} | Documento: {} | E-mail: {}",
            proposal.getName(),
            proposal.getDocument(),
            proposal.getEmail());

        var proposalDiserialized = clientProposalMapper.toDomain(proposal);

        System.out.println(proposalDiserialized);

//        validAndRegisterClientPortIn.validAndRegister(clientProposalMapper.toDomain(proposal));
    }
}

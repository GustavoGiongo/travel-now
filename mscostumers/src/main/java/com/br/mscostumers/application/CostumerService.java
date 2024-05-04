package com.br.mscostumers.application;

import com.br.mscostumers.domain.Costumer;
import com.br.mscostumers.infra.repository.CostumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostumerService {

    private final CostumerRepository costumerRepository;

    @Transactional
    public Costumer save(Costumer costumer){
        if(this.costumerRepository.findByCpf(costumer.getCpf()).isEmpty()){
            return costumerRepository.save(costumer);
        }
            return null;

    }

    public Optional<Costumer> getByCpf(String cpf){
        return costumerRepository.findByCpf(cpf);
    }
}

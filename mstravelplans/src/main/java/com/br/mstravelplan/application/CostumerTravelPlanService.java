package com.br.mstravelplan.application;

import com.br.mstravelplan.domain.CostumerPlan;
import com.br.mstravelplan.infra.CostumerTravelPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostumerTravelPlanService {
    private final CostumerTravelPlanRepository repository;

    public List<CostumerPlan> listCostumerTravelPlanByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}

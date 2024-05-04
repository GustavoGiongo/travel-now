package com.br.mstravelplan.infra;

import com.br.mstravelplan.domain.CostumerPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostumerTravelPlanRepository extends JpaRepository<CostumerPlan, Long> {
        List<CostumerPlan> findByCpf(String cpf);
}

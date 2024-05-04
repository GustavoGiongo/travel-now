package com.br.mstravelplan.application;

import com.br.mstravelplan.domain.TravelPlan;
import com.br.mstravelplan.infra.TravelPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelPlanService {

    private final TravelPlanRepository repository;

    @Transactional
    public TravelPlan save(TravelPlan travelPlan){
        return repository.save(travelPlan);
    }

    public List<TravelPlan> getTravelPlanLessEqual(Long income){
        var bigDecimalIncome = BigDecimal.valueOf(income);
        return  repository.findByIncomeLessThanEqual(bigDecimalIncome);

    }
}

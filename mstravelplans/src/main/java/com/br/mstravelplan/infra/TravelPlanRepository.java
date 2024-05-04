package com.br.mstravelplan.infra;

import com.br.mstravelplan.domain.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
    List<TravelPlan> findByIncomeLessThanEqual(BigDecimal bigDecimalIncome);
}

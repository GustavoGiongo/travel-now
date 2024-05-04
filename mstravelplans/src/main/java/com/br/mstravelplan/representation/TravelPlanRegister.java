package com.br.mstravelplan.representation;

import com.br.mstravelplan.domain.Destination;
import com.br.mstravelplan.domain.TravelPlan;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TravelPlanRegister {
    private String name;
    private Destination destination;
    private BigDecimal income;

    public TravelPlan toModel() {
        return new TravelPlan(name, destination, income);
    }
}


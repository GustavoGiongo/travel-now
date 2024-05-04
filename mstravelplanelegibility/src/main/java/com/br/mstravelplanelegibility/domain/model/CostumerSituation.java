package com.br.mstravelplanelegibility.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostumerSituation {
    private CostumerData costumer;
    private List<CostumerPlan> plans;

}

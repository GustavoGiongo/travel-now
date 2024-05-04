package com.br.mstravelplan.representation;


import com.br.mstravelplan.domain.CostumerPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlanResponse {
    private String name;
    private String destination;


    public static TravelPlanResponse fromModel(CostumerPlan model){
        return new TravelPlanResponse(
                model.getTravelPlan().getName(),
        model.getTravelPlan().getDestination().toString()
        );
    }
}

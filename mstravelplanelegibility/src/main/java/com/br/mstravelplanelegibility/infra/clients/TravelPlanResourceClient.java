package com.br.mstravelplanelegibility.infra.clients;

import com.br.mstravelplanelegibility.domain.model.CostumerPlan;
import com.br.mstravelplanelegibility.domain.model.TravelPlanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mstravelplans" , path = "/travel-plans")
public interface TravelPlanResourceClient {
    @GetMapping(params ="cpf")
    public ResponseEntity<List<CostumerPlan>> getCostumerPlan(@RequestParam("cpf") String cpf);
    @GetMapping(params = "income")
    public ResponseEntity<List<TravelPlanResponse>> getTravelPlanByIncome(@RequestParam("income")Long income);
}

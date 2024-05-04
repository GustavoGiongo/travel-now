package com.br.mstravelplan.application;

import java.util.List;
import java.util.stream.Collectors;

import com.br.mstravelplan.domain.CostumerPlan;
import com.br.mstravelplan.domain.TravelPlan;
import com.br.mstravelplan.representation.TravelPlanResponse;
import com.br.mstravelplan.representation.TravelPlanRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableEurekaClient
@RequiredArgsConstructor
@RequestMapping("travel-plans")
public class TravelPlanResource {

    private final TravelPlanService travelPlanService;
    private final CostumerTravelPlanService costumerTravelPlanService;
    public String status () {
        return "Ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody TravelPlanRegister request){
        var travelPlan = request.toModel();
        travelPlanService.save(travelPlan);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<TravelPlan>> getTravelPlanByIncome(@RequestParam("income")Long income){
        var list = travelPlanService.getTravelPlanLessEqual(income);
        return ResponseEntity.ok(list);

    }

    @GetMapping(params ="cpf")
    public ResponseEntity<List<TravelPlanResponse>> getCostumerTravelPlan(@RequestParam("cpf") String cpf){
        List<CostumerPlan> list = costumerTravelPlanService.listCostumerTravelPlanByCpf(cpf);
        List<TravelPlanResponse> resultList = list.stream()
                .map(TravelPlanResponse::fromModel)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}

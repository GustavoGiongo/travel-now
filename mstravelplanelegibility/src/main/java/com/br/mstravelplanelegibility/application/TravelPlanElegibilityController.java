package com.br.mstravelplanelegibility.application;

import com.br.mstravelplanelegibility.application.exception.CostumerDataNotFoundException;
import com.br.mstravelplanelegibility.application.exception.MicroservicesComunicationErrorException;
import com.br.mstravelplanelegibility.application.exception.RequestTravelException;
import com.br.mstravelplanelegibility.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("evaluation")
@RequiredArgsConstructor
public class TravelPlanElegibilityController {

    private final TravelPlanElegibilityService travelPlanElegibilityService;

    @GetMapping
    public String status() {
        return "Ok";
    }

    @GetMapping(value = "situation", params = "cpf")
    public ResponseEntity getCostumerSituation(@RequestParam("cpf") String cpf){
        CostumerSituation costumerSituation = null;
        try {
            costumerSituation = travelPlanElegibilityService.getCostumerSituation(cpf);
            return ResponseEntity.ok(costumerSituation);
        } catch (CostumerDataNotFoundException e) {
           return ResponseEntity.notFound().build();
        } catch (MicroservicesComunicationErrorException e) {
            return ResponseEntity.status(Objects.requireNonNull(HttpStatus.resolve(e.getStatus()))).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity evaluate(@RequestBody EvaluationData evaluationData){
        try {
            CostumerElegibility costumerElegibility = travelPlanElegibilityService.evaluateElegibility(evaluationData.getCpf(), evaluationData.getIncome());
            return ResponseEntity.ok(costumerElegibility);
        } catch (CostumerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesComunicationErrorException e) {
            return ResponseEntity.status(Objects.requireNonNull(HttpStatus.resolve(e.getStatus()))).body(e.getMessage());
        }
    }

    @PostMapping("request-travel")
    public ResponseEntity requesTravel(@RequestBody TravelSenderRequestData data){
        try {
            RequestTravelProtocol protocol = travelPlanElegibilityService.requestTravel(data);
            return ResponseEntity.ok(protocol);
        }catch (RequestTravelException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

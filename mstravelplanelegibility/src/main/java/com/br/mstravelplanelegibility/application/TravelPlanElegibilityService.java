package com.br.mstravelplanelegibility.application;

import com.br.mstravelplanelegibility.application.exception.CostumerDataNotFoundException;
import com.br.mstravelplanelegibility.application.exception.MicroservicesComunicationErrorException;
import com.br.mstravelplanelegibility.application.exception.RequestTravelException;
import com.br.mstravelplanelegibility.domain.model.*;
import com.br.mstravelplanelegibility.infra.clients.CostumerResourceClient;
import com.br.mstravelplanelegibility.infra.clients.TravelPlanResourceClient;
import com.br.mstravelplanelegibility.infra.mqueue.TravelSenderPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelPlanElegibilityService {
    private final CostumerResourceClient costumerResourceClient;
    private final TravelPlanResourceClient travelPlanResourceClient;
    private final TravelSenderPublisher travelSenderPublisher;
    public CostumerSituation getCostumerSituation(String cpf) throws CostumerDataNotFoundException, MicroservicesComunicationErrorException {

        try {
            ResponseEntity<CostumerData> clientDataResponse = costumerResourceClient.clientData(cpf);
            ResponseEntity<List<CostumerPlan>> clientCoverageResponse = travelPlanResourceClient.getCostumerPlan(cpf);

            return CostumerSituation.builder().costumer(clientDataResponse.getBody())
                    .plans(clientCoverageResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e){
            if(HttpStatus.NOT_FOUND.value() == e.status()) {
                throw new CostumerDataNotFoundException();
            }
            throw new MicroservicesComunicationErrorException(e.getMessage(), e.status());
        }

    }


    public CostumerElegibility evaluateElegibility(String cpf, Long income) throws CostumerDataNotFoundException, MicroservicesComunicationErrorException {
        try{
            ResponseEntity<CostumerData> costumerDataResponse = costumerResourceClient.clientData(cpf);
            ResponseEntity<List<TravelPlanResponse>> travelPlanByIncome = travelPlanResourceClient.getTravelPlanByIncome(income);
            List<TravelPlanResponse> plans = travelPlanByIncome.getBody();
            var aprovedPlans = plans.stream().map(plan ->{
               EligibleDestination eligibleDestination = new EligibleDestination();
                BigDecimal newPrice = (BigDecimal.valueOf(income).multiply(BigDecimal.valueOf(5)));
                if(Objects.requireNonNull(costumerDataResponse.getBody()).getAge() >= 50) {
                    BigDecimal incomeBigDecimal = BigDecimal.valueOf(income);
                    BigDecimal ageBigDecimal = BigDecimal.valueOf(costumerDataResponse.getBody().getAge());
                    BigDecimal  discount = incomeBigDecimal.divide(BigDecimal.valueOf(100).subtract(ageBigDecimal), RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(10));
                    newPrice = newPrice.subtract(discount);
                }

                eligibleDestination.setDestination(plan.getDestination());
                eligibleDestination.setStartingPrice(newPrice.toString());

               return eligibleDestination;
            }).collect(Collectors.toList());


            return new CostumerElegibility(aprovedPlans);

        }catch (FeignException.FeignClientException e){
            if(HttpStatus.NOT_FOUND.value() == e.status()) {
                throw new CostumerDataNotFoundException();
            }
            throw new MicroservicesComunicationErrorException(e.getMessage(), e.status());
        }

    }

    public RequestTravelProtocol requestTravel(TravelSenderRequestData data){
        try {
            travelSenderPublisher.travelRequest(data);
           return  new RequestTravelProtocol(UUID.randomUUID().toString());
        } catch (Exception e) {
            throw new RequestTravelException(e.getMessage());
        }
    }
}

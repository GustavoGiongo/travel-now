package com.br.mstravelplan.infra.mqueue;

import com.br.mstravelplan.domain.CostumerPlan;
import com.br.mstravelplan.domain.TravelPlan;
import com.br.mstravelplan.domain.TravelSenderRequestData;
import com.br.mstravelplan.infra.CostumerTravelPlanRepository;
import com.br.mstravelplan.infra.TravelPlanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TravelSenderSubscriber {

    private final CostumerTravelPlanRepository costumerTravelPlanRepository;
    private final TravelPlanRepository travelPlanRepository;

    @RabbitListener(queues = "${mq.queues.travel-sender}")
    public void getTravelSolicitation(@Payload String payLoad){
        System.out.println(payLoad);
       try{
           var mapper = new ObjectMapper();
           TravelSenderRequestData data = mapper.readValue(payLoad, TravelSenderRequestData.class);
           TravelPlan travelPlan = travelPlanRepository.findById(data.getId()).orElseThrow();
           CostumerPlan costumerPlan = new CostumerPlan();
           costumerPlan.setTravelPlan(travelPlan);
           costumerPlan.setCpf(data.getCpf());
           costumerPlan.setName(data.getName());

           costumerTravelPlanRepository.save(costumerPlan);

       }catch (JsonProcessingException e){

           log.error("Error trying to get sender request: {} ", e.getMessage());
       }

    }
}

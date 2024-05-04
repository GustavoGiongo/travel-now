package com.br.mstravelplanelegibility.infra.mqueue;

import com.br.mstravelplanelegibility.domain.model.TravelSenderRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TravelSenderPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueTravelSender;

    public void travelRequest(TravelSenderRequestData data) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(queueTravelSender.getActualName(), convertIntoJson(data));
    }

    private String convertIntoJson(TravelSenderRequestData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

}

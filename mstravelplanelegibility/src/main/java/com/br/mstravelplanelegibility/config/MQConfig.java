package com.br.mstravelplanelegibility.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.travel-sender}")
    private String queueTravelSender;

    @Bean
    public Queue travelSenderQueue(){
        return new Queue(queueTravelSender, true);
    }
}

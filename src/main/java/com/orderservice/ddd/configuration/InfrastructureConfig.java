package com.orderservice.ddd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orderservice.ddd.application.event.EventBus;
import com.orderservice.ddd.infrastructure.event.SimpleEventBus;

@Configuration
public class InfrastructureConfig {

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

}

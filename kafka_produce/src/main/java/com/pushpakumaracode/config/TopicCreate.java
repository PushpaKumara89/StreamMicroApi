package com.pushpakumaracode.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicCreate {

    @Bean
    public NewTopic CodeTopic(){
        return TopicBuilder.name("users").partitions(1).replicas(1)
                .build();
    }
}

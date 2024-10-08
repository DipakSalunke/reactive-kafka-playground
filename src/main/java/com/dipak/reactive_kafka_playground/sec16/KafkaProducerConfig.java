package com.dipak.reactive_kafka_playground.sec16;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public SenderOptions<String, OrderEvent> senderOptions(KafkaProperties kafkaProperties) {
        return SenderOptions.create(kafkaProperties.buildProducerProperties(null));
    }

    @Bean
    public ReactiveKafkaProducerTemplate<String, OrderEvent> producerTemplate(SenderOptions<String, OrderEvent> options) {
        return new ReactiveKafkaProducerTemplate<>(options);
    }
}

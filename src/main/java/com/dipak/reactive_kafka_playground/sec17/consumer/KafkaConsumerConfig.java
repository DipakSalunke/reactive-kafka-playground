package com.dipak.reactive_kafka_playground.sec17.consumer;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ReceiverOptions<String, DummyOrder> receiverOptions(KafkaProperties kafkaProperties) {
        return ReceiverOptions.<String, DummyOrder>create(kafkaProperties.buildConsumerProperties(null))
                .consumerProperty(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS, "false")
                // comment out USE_TYPE_INFO_HEADERS, VALUE_DEFAULT_TYPE prop and change DummyOrder references to OrderEvent
                // if want to use OrderEvent (producer typeId) as TYPE
                .consumerProperty(JsonDeserializer.USE_TYPE_INFO_HEADERS, false)
                .consumerProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, DummyOrder.class)
                .subscription(List.of("order-events"));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, DummyOrder> consumerTemplate(ReceiverOptions<String, DummyOrder> options) {
        return new ReactiveKafkaConsumerTemplate<>(options);
    }
}

package com.dipak.reactive_kafka_playground.sec01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

public class Lec01kafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(Lec01kafkaConsumer.class);

    public static void main(String[] args) {
        var consumerConfig = Map.<String, Object>of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, "demo-group",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest",
                ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "2"
        );
        var options = ReceiverOptions.create(consumerConfig)
                .subscription(List.of("order-events"));
        KafkaReceiver.create(options).receive()
                .doOnNext(r -> log.info("key: {} , value : {}", r.key(), r.value()))
                .doOnNext(r->r.receiverOffset().acknowledge()).subscribe();

    }
}

package com.dipak.reactive_kafka_playground;

import com.dipak.reactive_kafka_playground.sec17.producer.OrderEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.test.StepVerifier;

@TestPropertySource(properties = "app=producer")
public class OrderEventProducerTest extends AbstractIT{

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducerTest.class);

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void producerTest(){
        KafkaReceiver<String, OrderEvent> receiver = createReceiver("order-events");
        var orderEvents = receiver.receive()
                .take(10)
                .doOnNext(r -> log.info("key: {}, value: {}", r.key(), r.value()));
        StepVerifier.create(orderEvents)
                .consumeNextWith(r -> Assertions.assertNotNull(r.value().orderId()))
                .expectNextCount(9)
                .expectComplete();
    }
}

package com.dipak.reactive_kafka_playground.sec17.consumer;

import java.util.UUID;

public record DummyOrder(
        UUID orderId,
        long customerId
) {
}

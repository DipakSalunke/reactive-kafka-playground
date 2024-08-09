package com.dipak.reactive_kafka_playground.sec16;

import java.util.UUID;

public record DummyOrder(
        UUID orderId,
        long customerId
) {
}

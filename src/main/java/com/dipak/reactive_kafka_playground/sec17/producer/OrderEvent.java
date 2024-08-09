package com.dipak.reactive_kafka_playground.sec17.producer;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderEvent(
        UUID orderId,
        long customerId,
        LocalDateTime orderDate
) {
}

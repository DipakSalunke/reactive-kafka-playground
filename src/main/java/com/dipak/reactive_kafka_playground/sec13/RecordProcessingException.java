package com.dipak.reactive_kafka_playground.sec13;

import reactor.kafka.receiver.ReceiverRecord;

public class RecordProcessingException extends RuntimeException {
    private final ReceiverRecord<?, ?> record;

    public RecordProcessingException(ReceiverRecord<?, ?> record, Exception e) {
        super(e);
        this.record = record;
    }

    public <K,V> ReceiverRecord<K, V> getRecord() {
        return (ReceiverRecord<K, V>) record;
    }
}

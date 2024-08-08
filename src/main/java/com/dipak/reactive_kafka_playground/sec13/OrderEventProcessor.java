package com.dipak.reactive_kafka_playground.sec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Random;

public class OrderEventProcessor {
    private static final Logger log = LoggerFactory.getLogger(OrderEventProcessor.class);
    private final ReactiveDeadLetterTopicProducer<String, String> deadLetterTopicProducer;
    Random random = new Random();
    public OrderEventProcessor(ReactiveDeadLetterTopicProducer<String, String> deadLetterTopicProducer) {
        this.deadLetterTopicProducer = deadLetterTopicProducer;
    }

    public Mono<Void> process(ReceiverRecord<String, String> record){
        return Mono.just(record)
                .doOnNext(r -> {
                    //if(r.key().endsWith("5"))
                    if(random.nextInt(3) == 2)
                        throw new RuntimeException("processing Exception for order : "+ r.key());
                    log.info("key: {} , value : {}, topic :{}", r.key(), r.value(), r.topic());
                    r.receiverOffset().acknowledge();
                })
                .onErrorMap(ex -> new RecordProcessingException(record, (Exception) ex))
                .transform(this.deadLetterTopicProducer.recordProcessingHandler());

    }
}

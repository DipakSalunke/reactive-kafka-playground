package com.dipak.reactive_kafka_playground.sec05;

public class KafkaConsumerGroup {
    private static class KafkaConsumerGroup1 {
        public static void main(String[] args) {
            kafkaConsumer.start("1");
        }
    }

    private static class KafkaConsumerGroup2 {
        public static void main(String[] args) {
            kafkaConsumer.start("2");
        }
    }

    private static class KafkaConsumerGroup3 {
        public static void main(String[] args) {
            kafkaConsumer.start("3");
        }
    }
}


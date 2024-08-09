package com.dipak.reactive_kafka_playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dipak.reactive_kafka_playground.sec17.${app}")
public class ReactiveKafkaPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveKafkaPlaygroundApplication.class, args);
	}

}

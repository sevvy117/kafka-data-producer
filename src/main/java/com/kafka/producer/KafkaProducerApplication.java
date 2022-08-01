package com.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class KafkaProducerApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

}

package com.kafka.producer.configuration;

import com.kafka.producer.BookAvro;
import com.kafka.producer.exception.ConfigException;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaConfiguration {

    @Bean
    public KafkaProducer<String, BookAvro> kafkaProducer() {
        Properties props = new Properties();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            var file = new File(classLoader.getResource("producer.properties").getFile());
            props.load(new FileReader(file));
        } catch (NullPointerException | IOException e) {
            throw new ConfigException("Unable to load properties.", e);
        }

        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        return new KafkaProducer<>(props);
    }
}

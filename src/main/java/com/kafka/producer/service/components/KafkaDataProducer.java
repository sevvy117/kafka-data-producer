package com.kafka.producer.service.components;

import com.kafka.producer.BookAvro;
import com.kafka.producer.client.VolumeInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaDataProducer {

    private final BookAvroConverter bookAvroConverter;
    private final KafkaProducer<String, BookAvro> kafkaProducer;

    public void produceToKafka(String topic, VolumeInfo volumeInfo) {
        var bookAvro = bookAvroConverter.convert(volumeInfo);
        var record = new ProducerRecord<>(topic, volumeInfo.getTitle(), bookAvro);

        try {
            kafkaProducer.send(record);
            kafkaProducer.flush();
            log.info("Successfully published message {} to topic {}", bookAvro, topic);
        } catch (Exception e) {
            log.error("Failed to send message {}", bookAvro);
        }
    }
}

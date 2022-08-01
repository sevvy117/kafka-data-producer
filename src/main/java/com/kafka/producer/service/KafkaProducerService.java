package com.kafka.producer.service;

import com.kafka.producer.client.GoogleBooksClient;
import com.kafka.producer.configuration.properties.BooksApiProperties;
import com.kafka.producer.service.components.KafkaDataProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
@RequiredArgsConstructor
public class KafkaProducerService implements ApplicationListener<ApplicationReadyEvent> {

    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private static final int MAX_RESULTS = 40;
    private static final String GOOGLE_BOOKS_SEARCH_FILTER = "the";

    @Value("${topic-name}")
    private String topicName;

    private final GoogleBooksClient googleBooksClient;
    private final BooksApiProperties booksApiProperties;
    private final KafkaDataProducer kafkaDataProducer;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        var startingIndex = 0;
        var volumesResponse = googleBooksClient.getVolumes(booksApiProperties.getApiKey(), GOOGLE_BOOKS_SEARCH_FILTER, MAX_RESULTS, startingIndex);

        while(startingIndex < volumesResponse.getTotalItems()){
            int finalStartingIndex = startingIndex;
            try {
                volumesResponse = executorService.schedule( () -> googleBooksClient.getVolumes(booksApiProperties.getApiKey(), GOOGLE_BOOKS_SEARCH_FILTER, MAX_RESULTS, finalStartingIndex), 45, TimeUnit.SECONDS).get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("Encountered an error while getting books", e);
                continue;
            }

            for (var item : volumesResponse.getItems()) {
                var book = googleBooksClient.getVolume(item.getId());
                kafkaDataProducer.produceToKafka(topicName, book.getVolumeInfo());
            }
            startingIndex += MAX_RESULTS;
        }
    }
}

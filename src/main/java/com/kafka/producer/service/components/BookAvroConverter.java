package com.kafka.producer.service.components;

import com.kafka.producer.BookAvro;
import com.kafka.producer.client.VolumeInfo;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BookAvroConverter {

    public BookAvro convert(VolumeInfo volumeInfo) {
        return BookAvro.newBuilder()
                .setTitle(volumeInfo.getTitle())
                .setAuthors(volumeInfo.getAuthors() == null ? Collections.emptyList() : volumeInfo.getAuthors())
                .setPublisher(volumeInfo.getPublisher() == null ? "" : volumeInfo.getPublisher())
                .setPublishedDate(volumeInfo.getPublishedDate() == null ? "" : volumeInfo.getPublishedDate())
                .setPageCount(volumeInfo.getPageCount() == null ? 0.0 : volumeInfo.getPageCount())
                .setAverageRating(volumeInfo.getAverageRating() == null ? 0.0 : volumeInfo.getAverageRating())
                .setRatingsCount(volumeInfo.getRatingsCount() == null ? 0.0 : volumeInfo.getRatingsCount())
                .build();
    }
}

package com.kafka.producer.client;

import lombok.Data;

import java.util.List;

@Data
public class VolumeInfo {

    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private Double pageCount;
    private Double averageRating;
    private Double ratingsCount;
}

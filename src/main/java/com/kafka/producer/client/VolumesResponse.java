package com.kafka.producer.client;

import lombok.Data;

import java.util.List;

@Data
public class VolumesResponse {

    private String kind;
    private Double totalItems;
    private List<Item> items;
}

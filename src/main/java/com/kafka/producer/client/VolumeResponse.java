package com.kafka.producer.client;

import lombok.Data;

@Data
public class VolumeResponse {

    private String kind;
    private String id;
    private VolumeInfo volumeInfo;
}

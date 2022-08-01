package com.kafka.producer.client;

import lombok.Data;

@Data
public class Item {

    private String kind;
    private String id;
    private String selfLink;
}

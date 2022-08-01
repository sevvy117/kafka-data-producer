package com.kafka.producer.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "books-api")
public class BooksApiProperties {

    private String url;
    private String apiKey;
}

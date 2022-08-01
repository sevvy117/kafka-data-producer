package com.kafka.producer.configuration;

import com.kafka.producer.client.GoogleBooksClient;
import com.kafka.producer.configuration.properties.BooksApiProperties;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GoogleBooksClientConfiguration {

    private final BooksApiProperties booksApiProperties;

    @Bean
    public GoogleBooksClient googleBooksClient(){
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .target(GoogleBooksClient.class, booksApiProperties.getUrl());
    }
}

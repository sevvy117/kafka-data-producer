package com.kafka.producer.client;

import feign.Param;
import feign.RequestLine;

public interface GoogleBooksClient {

    @RequestLine("GET /volumes?q={keyword}&key={apiKey}&maxResults={maxResults}&startIndex={startIndex}")
    VolumesResponse getVolumes(@Param("apiKey") String apiKey, @Param("keyword") String keyword, @Param("maxResults") int maxResults, @Param("startIndex") int startIndex);

    @RequestLine("GET /volumes/{id}")
    VolumeResponse getVolume(@Param("id") String id);
}

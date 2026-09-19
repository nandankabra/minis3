package com.misi3.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class StorageNodeClient {

    private final RestClient restClient;

    public StorageNodeClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public String health() {
        return restClient.get()
                .uri("/storage/health")
                .retrieve()
                .body(String.class);
    }
}

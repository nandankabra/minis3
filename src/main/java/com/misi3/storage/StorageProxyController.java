package com.misi3.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageProxyController {

    private final StorageNodeClient storageNodeClient;

    public StorageProxyController(StorageNodeClient storageNodeClient) {
        this.storageNodeClient = storageNodeClient;
    }

    @GetMapping("/api/storage/health")
    public String storageHealth() {
        return storageNodeClient.health();
    }
}
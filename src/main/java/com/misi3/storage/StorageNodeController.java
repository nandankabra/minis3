package com.misi3.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageNodeController {

    @GetMapping("/storage/health")
    public String health() {
        return "Storage Node Running";
    }
}
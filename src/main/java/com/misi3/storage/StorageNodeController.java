package com.misi3.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageNodeController {
    @GetMapping("/health")
    public String health() {
        return "Storage Node Running";
    }
}

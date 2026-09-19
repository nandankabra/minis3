package com.misi3.storage;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/storage")
public class StorageNodeController {

    private final Path root = Paths.get("./storage-data");

    @PutMapping("/{bucket}/{objectKey}")
    public String upload(
            @PathVariable String bucket,
            @PathVariable String objectKey,
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        Files.createDirectories(root.resolve(bucket));

        Path objectPath = root.resolve(bucket).resolve(objectKey);

        file.transferTo(objectPath);

        return "Uploaded: " + objectKey;
    }

    @GetMapping("/health")
    public String health() {
        return "Storage Node Running";
    }
}
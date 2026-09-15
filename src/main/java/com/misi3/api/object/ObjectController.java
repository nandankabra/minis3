package com.misi3.api.object;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/buckets/{bucketName}/objects")
public class ObjectController {

    private final ObjectService objectService;

    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @PutMapping(
            value = "/{objectKey}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadObject(
            @PathVariable String bucketName,
            @PathVariable String objectKey,
            @RequestParam("file") MultipartFile multipartFile
    ) throws Exception {

        Path storedPath =
                objectService.upload(
                        bucketName,
                        objectKey,
                        multipartFile
                );

        return ResponseEntity.ok(
                "Object stored successfully: " + storedPath
        );
    }

    @GetMapping("/{objectKey}")
    public ResponseEntity<Resource> downloadObject(
            @PathVariable String bucketName,
            @PathVariable String objectKey
    ) {

        Path path =
                objectService.getObject(
                        bucketName,
                        objectKey
                );

        Resource resource = new FileSystemResource(path);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + objectKey + "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @DeleteMapping("/{objectKey}")
    public ResponseEntity<String> deleteObject(
            @PathVariable String bucketName,
            @PathVariable String objectKey
    ) throws IOException {

        objectService.delete(
                bucketName,
                objectKey
        );

        return ResponseEntity.ok(
                "Object deleted successfully"
        );
    }
}
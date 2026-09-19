package com.misi3.api.bucket;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/buckets")
public class BucketController {

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bucket createBucket(@RequestBody Bucket bucket) {
        return bucketService.createBucket(bucket.getName());
    }

    @GetMapping
    public Collection<Bucket> getBuckets() {
        return bucketService.getBuckets();
    }
}
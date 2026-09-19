package com.misi3.api.bucket;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BucketService {

    private final BucketRepository bucketRepository;

    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public Bucket createBucket(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Bucket name cannot be empty");
        }

        if (bucketRepository.exists(name)) {
            throw new IllegalArgumentException("Bucket already exists");
        }

        Bucket bucket = new Bucket(name);
        return bucketRepository.save(bucket);
    }

    public Collection<Bucket> getBuckets() {
        return bucketRepository.findAll();
    }
}
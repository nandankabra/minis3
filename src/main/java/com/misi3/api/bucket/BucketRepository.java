package com.misi3.api.bucket;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BucketRepository {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public Bucket save(Bucket bucket) {
        buckets.put(bucket.getName(), bucket);
        return bucket;
    }

    public boolean exists(String name) {
        return buckets.containsKey(name);
    }

    public Bucket findByName(String name) {
        return buckets.get(name);
    }

    public Collection<Bucket> findAll() {
        return buckets.values();
    }
}
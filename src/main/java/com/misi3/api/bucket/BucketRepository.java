package com.misi3.api.bucket;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BucketRepository {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public Bucket save(Bucket bucket) {
        buckets.put(bucket.getName(), bucket);

        System.out.println("Saved bucket: " + bucket.getName());
        System.out.println("All buckets: " + buckets.keySet());

        return bucket;
    }

    public boolean exists(String name) {
        System.out.println("Checking bucket: " + name);
        System.out.println("Available buckets: " + buckets.keySet());

        return buckets.containsKey(name);
    }

    public Bucket findByName(String name) {
        return buckets.get(name);
    }
}
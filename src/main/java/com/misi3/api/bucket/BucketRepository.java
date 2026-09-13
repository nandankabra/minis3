package com.misi3.api.bucket;

import org.springframework.boot.ssl.SslBundleKey;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BucketRepository {
    private  final Map<String ,Bucket> buckets= new ConcurrentHashMap<>();
    public Bucket save(Bucket bucket){
        bucket.put(bucket.getName(),bucket);
        return bucket;
    }
    public Boolean exists(String name ){
        return buckets.containsKey(name);
    }
    public Bucket findByName(String name){
        return buckets.get(name);
    }
}

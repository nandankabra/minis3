package com.misi3.api.bucket;

public class Bucket {
    private String name;

    public Bucket() {
    }

    public Bucket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void put(String name, Bucket bucket) {
    }
}

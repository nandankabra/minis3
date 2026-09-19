package com.misi3.api.object;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LocalStorageService {

    private final Path rootDirectory;

    public LocalStorageService(
            @Value("${misi3.storage.root}") String storageRoot
    ) {

        this.rootDirectory = Paths
                .get(storageRoot)
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(rootDirectory);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not create storage directory",
                    e
            );
        }
    }

    public Path store(
            String bucketName,
            String objectKey,
            MultipartFile file
    ) throws IOException {

        Path bucketDirectory =
                rootDirectory.resolve(bucketName);

        Files.createDirectories(bucketDirectory);

        Path objectPath =
                bucketDirectory.resolve(objectKey);

        try (InputStream inputStream = file.getInputStream()) {

            Files.copy(
                    inputStream,
                    objectPath,
                    StandardCopyOption.REPLACE_EXISTING
            );
        }

        return objectPath;
    }

    public Path getObject(
            String bucketName,
            String objectKey
    ) {

        return rootDirectory
                .resolve(bucketName)
                .resolve(objectKey);
    }

    public void delete(
            String bucketName,
            String objectKey
    ) throws IOException {

        Path objectPath =
                getObject(bucketName, objectKey);

        Files.deleteIfExists(objectPath);
    }
    /*
    public List<String> listObjects(
            String bucketName
    ) throws IOException {

        Path bucketPath =
                rootDirectory.resolve(bucketName);

        if (!Files.exists(bucketPath)) {
            return List.of();
        }

        try (Stream<Path> stream = Files.list(bucketPath)) {
            return stream
                    .map(path -> path.getFileName().toString())
                    .toList();
        }
    }

     */
    public List<String> listObjects(String bucketName)
            throws IOException {

        Path bucketPath = rootDirectory.resolve(bucketName);

        System.out.println("Root directory = " + rootDirectory);
        System.out.println("Bucket path = " + bucketPath);
        System.out.println("Exists = " + Files.exists(bucketPath));

        if (!Files.exists(bucketPath)) {
            return List.of();
        }

        try (Stream<Path> stream = Files.list(bucketPath)) {
            return stream
                    .map(path -> path.getFileName().toString())
                    .toList();
        }
    }
}
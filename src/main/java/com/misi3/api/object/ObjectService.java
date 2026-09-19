package com.misi3.api.object;

import com.misi3.api.bucket.BucketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class ObjectService {
    private final BucketRepository bucketRepository;
    private  final LocalStorageService localStorageService;

    public ObjectService(BucketRepository bucketRepository, LocalStorageService localStorageService) {
        this.bucketRepository = bucketRepository;
        this.localStorageService = localStorageService;
    }
    /*
    public Path upload(String bucketName , String objectKey , MultipartFile multipartFile) throws Exception {
       if(!bucketRepository.exists(bucketName)){
           throw new IllegalArgumentException("Bucket doesn't exist" + bucketName);
       }
       if(multipartFile.isEmpty()){
           throw  new IllegalArgumentException("File can't be empty");
       }
       return localStorageService.store(bucketName,objectKey,multipartFile);
    }*/

    //temp code
    public Path upload(String bucketName , String objectKey , MultipartFile multipartFile) throws Exception {

        System.out.println("ObjectService Repo = " + bucketRepository.hashCode());

        if(!bucketRepository.exists(bucketName)){
            throw new IllegalArgumentException("Bucket doesn't exist " + bucketName);
        }

        return localStorageService.store(bucketName,objectKey,multipartFile);
    }
    //temp code end here

    public Path getObject(String bucketName , String objectKey){
        Path path = localStorageService.getObject(bucketName,objectKey);
        if(!path.toFile().exists()){
            throw new IllegalArgumentException("Object not found");
        }
        return path;
    }
    public void delete(String bucketName , String objectKey)throws IOException{
        localStorageService.delete(bucketName,objectKey );
    }
    /*
    public List<String> listObjects(String bucketName)
            throws IOException {

        if (!bucketRepository.exists(bucketName)) {
            throw new IllegalArgumentException(
                    "Bucket doesn't exist " + bucketName
            );
        }

        return localStorageService.listObjects(bucketName);
    }
    */
    public List<String> listObjects(String bucketName)
            throws IOException {

        System.out.println("========== LIST OBJECTS ==========");
        System.out.println("Bucket = " + bucketName);
        System.out.println("Bucket exists = " + bucketRepository.exists(bucketName));

        return localStorageService.listObjects(bucketName);
    }

}

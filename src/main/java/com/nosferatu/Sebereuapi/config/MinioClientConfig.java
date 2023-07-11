package com.nosferatu.Sebereuapi.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MinioClientConfig {

    @Value("${application.minio-url}")
    public String minioUrl;

    @Value("${application.minio-access-key}")
    private String minioAccessKey;

    @Value("${application.minio-secret-key}")
    private String minioSecretKey;

    @Value("${application.minio-default-bucket}")
    public String minioDefaultBucket;

    @Bean
    public MinioClient init() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder().endpoint(minioUrl)
                .credentials(minioAccessKey, minioSecretKey).build();

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioDefaultBucket).build())) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(minioDefaultBucket)
                            .build());
        }

        return minioClient;
    }
}

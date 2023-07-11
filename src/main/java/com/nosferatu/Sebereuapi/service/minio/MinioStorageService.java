package com.nosferatu.Sebereuapi.service.minio;

import com.nosferatu.Sebereuapi.domain.model.MinioStoredResult;
import com.nosferatu.Sebereuapi.exception.FileEmptyException;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Objects;

@Service
public class MinioStorageService {

    @Value("${application.minio-default-store-path}")
    public String minioDefaultStorePath;

    @Value("${application.minio-default-bucket}")
    public String minioDefaultBucket;

    private final MinioClient minioClient;

    public MinioStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public MinioStoredResult store(MultipartFile file, String title) {

        if (Objects.isNull(file) || file.isEmpty()) throw new FileEmptyException();

        String pathToSave = "/" + minioDefaultStorePath;
        String fileNameToSave = String.format("%s-%s",
                Normalizer.normalize(
                        title.replace(' ', '-')
                                .toLowerCase(), Normalizer.Form.NFD
                ).replaceAll("[^\\p{ASCII}]", "")
                , System.nanoTime());
        String fullFilePath = pathToSave + "/" + fileNameToSave;

        try {
            ObjectWriteResponse stored = minioClient.putObject(PutObjectArgs.builder()
                    .object(String.format(fullFilePath))
                    .contentType(file.getContentType())
                    .bucket(minioDefaultBucket)
                    .stream(file.getInputStream(), file.getSize(), 10000000)
                    .build());

            return new MinioStoredResult(pathToSave, fileNameToSave, fullFilePath, stored);

        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

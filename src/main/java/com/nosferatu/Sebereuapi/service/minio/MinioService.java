package com.nosferatu.Sebereuapi.service.minio;

import com.nosferatu.Sebereuapi.domain.model.MinioStoredResult;
import com.nosferatu.Sebereuapi.exception.FileEmptyException;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Objects;

@Service
public class MinioService {

    @Value("${application.minio-default-bucket}")
    public String minioDefaultBucket;

    private final MinioClient minioClient;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public MinioStoredResult store(MultipartFile file, String title, String storePath, boolean withNanoTime) {

        if (Objects.isNull(file) || file.isEmpty()) throw new FileEmptyException();

        String pathToSave = "/" + storePath;
        String fileNameToSave = generateFileName(
                Normalizer.normalize(
                        title.replace(' ', '-')
                                .toLowerCase(), Normalizer.Form.NFD
                ).replaceAll("[^\\p{ASCII}]", ""), withNanoTime
        );

        String fullFilePath = pathToSave + "/" + fileNameToSave;

        try {
            ObjectWriteResponse stored = minioClient.putObject(PutObjectArgs.builder()
                    .object(String.format(fullFilePath))
                    .contentType(file.getContentType())
                    .bucket(minioDefaultBucket)
                    .stream(file.getInputStream(), file.getSize(), 90000000)
                    .build());

            return new MinioStoredResult(pathToSave, fileNameToSave, fullFilePath, stored);

        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public GetObjectResponse get(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioDefaultBucket)
                        .object(objectName)
                        .build()
        );
    }

    private String generateFileName(String formattedFileName, boolean withNanoSecond){
        return withNanoSecond ? String.format("%s-%s",formattedFileName, System.nanoTime())
                : String.format("%s",formattedFileName);

    }
}

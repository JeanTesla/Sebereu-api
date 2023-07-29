package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.exception.ContributionNotFoundException;
import com.nosferatu.Sebereuapi.exception.MinioGenericErrorException;
import com.nosferatu.Sebereuapi.exception.UploadNotFoundException;
import com.nosferatu.Sebereuapi.service.minio.MinioService;
import io.minio.GetObjectResponse;
import io.minio.errors.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class GetFileContributionService {

    private final MinioService minioService;

    private final ContributionRepository contributionRepository;

    private final FileUploadRepository fileUploadRepository;

    public GetFileContributionService(
            MinioService minioService,
            ContributionRepository contributionRepository,
            FileUploadRepository fileUploadRepository
    ) {
        this.minioService = minioService;
        this.contributionRepository = contributionRepository;
        this.fileUploadRepository = fileUploadRepository;
    }

    public ResponseEntity<InputStreamResource> execute(String contributionId) {

        UUID uploadId = contributionRepository.findById(contributionId)
                .orElseThrow(ContributionNotFoundException::new).getUploadId();

        FileUpload upload = fileUploadRepository.findById(uploadId)
                .orElseThrow(UploadNotFoundException::new);

        String fullFilePath = String.format("%s/%s", upload.getSavedPath(), upload.getSavedFileTitle());

        try (GetObjectResponse object = minioService.get(fullFilePath)) {

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(upload.getMimeType()))
                    .body(new InputStreamResource(new ByteArrayInputStream(object.readAllBytes())));
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new MinioGenericErrorException();
        }
    }
}

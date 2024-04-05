package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.ContributionNotFoundException;
import com.nosferatu.Sebereuapi.exception.MinioGenericErrorException;
import com.nosferatu.Sebereuapi.exception.UploadNotFoundException;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
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

    private final UserRepository userRepository;

    private final IncreaseContributionViewService increaseContributionViewService;

    public GetFileContributionService(
            MinioService minioService,
            IncreaseContributionViewService increaseContributionViewService,
            ContributionRepository contributionRepository,
            FileUploadRepository fileUploadRepository,
            UserRepository userRepository) {
        this.minioService = minioService;
        this.contributionRepository = contributionRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.increaseContributionViewService = increaseContributionViewService;
        this.userRepository = userRepository;
    }

    public ResponseEntity<InputStreamResource> execute(String contributionId, UUID requestUserId) {

        Contribution contribution = contributionRepository.findById(contributionId)
                .orElseThrow(ContributionNotFoundException::new);

        userRepository.findByUserId(requestUserId)
                .orElseThrow(UserNotFoundException::new);

        FileUpload upload = fileUploadRepository.findById(contribution.getUploadId())
                .orElseThrow(UploadNotFoundException::new);

        String fullFilePath = String.format("%s/%s", upload.getSavedPath(), upload.getSavedFileTitle());

        try (GetObjectResponse object = minioService.get(fullFilePath)) {

            if(!contribution.getUserId().equals(requestUserId)){
                increaseContributionViewService.execute(contributionId);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(upload.getMimeType()))
                    .body(new InputStreamResource(new ByteArrayInputStream(object.readAllBytes())));
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new MinioGenericErrorException();
        }
    }
}

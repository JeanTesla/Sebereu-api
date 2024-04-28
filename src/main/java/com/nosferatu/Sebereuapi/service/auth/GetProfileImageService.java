package com.nosferatu.Sebereuapi.service.auth;

import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.MinioGenericErrorException;
import com.nosferatu.Sebereuapi.exception.UploadNotFoundException;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import com.nosferatu.Sebereuapi.service.minio.MinioService;
import io.minio.GetObjectResponse;
import io.minio.errors.MinioException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

@Service
public class GetProfileImageService{
    private final MinioService minioService;

    private final FileUploadRepository fileUploadRepository;

    private final UserRepository userRepository;

    public GetProfileImageService(
            MinioService minioService,
            FileUploadRepository fileUploadRepository,
            UserRepository userRepository) {
        this.minioService = minioService;

        this.fileUploadRepository = fileUploadRepository;

        this.userRepository = userRepository;
    }

    public ResponseEntity<InputStreamResource> execute(UUID userId) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        if(Objects.isNull(user.getProfileImageUploadId())){
            throw new UploadNotFoundException();
        }

        FileUpload upload = fileUploadRepository.findById(user.getProfileImageUploadId())
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



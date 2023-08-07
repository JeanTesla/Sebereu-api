package com.nosferatu.Sebereuapi.service.auth;

import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.model.MinioStoredResult;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import com.nosferatu.Sebereuapi.service.minio.MinioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UploadProfileImageService {

    private final MinioService minioStorageService;

    private final FileUploadRepository fileUploadRepository;

    private final UserRepository userRepository;

    @Value("${application.minio-default-store-path-profiles}")
    public String minioDefaultStorePathProfiles;

    public UploadProfileImageService(
            MinioService minioStorageService,
            FileUploadRepository fileUploadRepository,
            UserRepository userRepository
    ) {
        this.minioStorageService = minioStorageService;
        this.fileUploadRepository = fileUploadRepository;
        this.userRepository = userRepository;
    }

    public void execute(MultipartFile file, UUID userId) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        String uploadTitle = user.getUserId().toString();

        MinioStoredResult savedObject = minioStorageService.store(
                file,
                uploadTitle,
                minioDefaultStorePathProfiles,
                false);

        FileUpload uploadedFile = fileUploadRepository.save(
                FileUpload.builder()
                        .uploadFileTitle(uploadTitle)
                        .savedFileTitle(savedObject.getStoredFileName())
                        .savedPath(savedObject.getStoredPath())
                        .mimeType(file.getContentType())
                        .size(file.getSize())
                        .build()
        );

        user.setProfileImageUploadId(uploadedFile.getFileUploadId());
        userRepository.save(user);
    }
}
package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.response.FileUploadResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import com.nosferatu.Sebereuapi.domain.model.MinioStoredResult;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.service.minio.MinioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileContributionService {

    private final MinioService minioStorageService;

    private final FileUploadRepository fileUploadRepository;

    @Value("${application.minio-default-store-path-contribution}")
    public String minioDefaultStorePathContribution;

    public UploadFileContributionService(
            MinioService minioStorageService,
            FileUploadRepository fileUploadRepository
    ) {
        this.minioStorageService = minioStorageService;
        this.fileUploadRepository = fileUploadRepository;
    }

    public FileUploadResponseDTO execute(MultipartFile file, String uploadTitle) {
        MinioStoredResult savedObject = minioStorageService.store(
                file,
                uploadTitle,
                minioDefaultStorePathContribution,
                true);

        return FileUploadResponseDTO.fromEntity(persistFileData(
                savedObject, uploadTitle, file.getSize(), file.getContentType()));
    }

    private FileUpload persistFileData(MinioStoredResult savedObject, String uploadFileTitle,
                                       Long uploadFileSize, String mimeType) {
        return fileUploadRepository.save(
                FileUpload.builder()
                        .uploadFileTitle(uploadFileTitle)
                        .savedFileTitle(savedObject.getStoredFileName())
                        .savedPath(savedObject.getStoredPath())
                        .mimeType(mimeType)
                        .size(uploadFileSize)
                        .build()
        );
    }
}

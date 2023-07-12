package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.FileUploadResponseDTO;
import com.nosferatu.Sebereuapi.service.contribution.SaveContributionService;
import com.nosferatu.Sebereuapi.service.contribution.UploadFileContributionService;
import com.nosferatu.Sebereuapi.service.minio.MinioService;
import io.minio.errors.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/contribution")
public class ContributionController {

    private final SaveContributionService saveContributionService;

    private final UploadFileContributionService uploadFileContributionService;

    private final MinioService minioService;

    public ContributionController(
            SaveContributionService saveContributionService,
            UploadFileContributionService uploadFileContributionService,
            MinioService minioService) {
        this.saveContributionService = saveContributionService;
        this.uploadFileContributionService = uploadFileContributionService;
        this.minioService = minioService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<InputStreamResource> getContribution() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioService.teste();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveContribution(@RequestBody ContributionRequestDTO contributionRequestDTO){
        saveContributionService.exec(contributionRequestDTO);
    }

    @PostMapping(value = "/upload-file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FileUploadResponseDTO contributionFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String uploadTitle){
        return uploadFileContributionService.execute(file, uploadTitle);
    }
}
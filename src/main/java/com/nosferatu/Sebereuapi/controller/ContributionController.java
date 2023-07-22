package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.FileUploadResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.service.contribution.GetAllContributionService;
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
import java.util.List;

@RestController
@RequestMapping(value = "/contribution")
public class ContributionController {

    private final SaveContributionService saveContributionService;

    private final UploadFileContributionService uploadFileContributionService;

    private final GetAllContributionService getAllContributionService;

    public ContributionController(
            SaveContributionService saveContributionService,
            UploadFileContributionService uploadFileContributionService,
            GetAllContributionService getAllContributionService) {
        this.saveContributionService = saveContributionService;
        this.uploadFileContributionService = uploadFileContributionService;
        this.getAllContributionService = getAllContributionService;
    }

    @GetMapping(value = "/")
    public List<Contribution> getAllContribution() {
        return getAllContributionService.execute();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveContribution(@RequestBody ContributionRequestDTO contributionRequestDTO){
        saveContributionService.execute(contributionRequestDTO);
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
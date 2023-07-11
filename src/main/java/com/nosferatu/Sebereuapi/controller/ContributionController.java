package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.FileUploadResponseDTO;
import com.nosferatu.Sebereuapi.service.contribution.SaveContributionService;
import com.nosferatu.Sebereuapi.service.contribution.UploadFileContributionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/contribution")
public class ContributionController {

    private final SaveContributionService saveContributionService;

    private final UploadFileContributionService uploadFileContributionService;

    public ContributionController(
            SaveContributionService saveContributionService,
            UploadFileContributionService uploadFileContributionService) {
        this.saveContributionService = saveContributionService;
        this.uploadFileContributionService = uploadFileContributionService;
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
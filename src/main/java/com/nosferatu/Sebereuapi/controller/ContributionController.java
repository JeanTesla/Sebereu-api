package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.ContributionDetailResponseDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.ContributionResponseDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.FileUploadResponseDTO;
import com.nosferatu.Sebereuapi.service.contribution.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/contribution")
public class ContributionController {

    private final SaveContributionService saveContributionService;

    private final UploadFileContributionService uploadFileContributionService;

    private final GetAllContributionService getAllContributionService;

    private final GetFileContributionService getFileContributionService;

    private final GetContributionService getContributionService;

    private final IncreaseContributionViewService increaseContributionViewService;

    public ContributionController(
            SaveContributionService saveContributionService,
            UploadFileContributionService uploadFileContributionService,
            GetAllContributionService getAllContributionService,
            GetFileContributionService getFileContributionService,
            GetContributionService getContributionService,
            IncreaseContributionViewService increaseContributionViewService) {
        this.saveContributionService = saveContributionService;
        this.uploadFileContributionService = uploadFileContributionService;
        this.getAllContributionService = getAllContributionService;
        this.getFileContributionService = getFileContributionService;
        this.getContributionService = getContributionService;
        this.increaseContributionViewService = increaseContributionViewService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ContributionResponseDTO> getAllContribution(
            @RequestParam(value = "userId") UUID userId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        System.out.println(userId);
        return getAllContributionService.execute(userId, page, size);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void saveContribution(@RequestBody ContributionRequestDTO contributionRequestDTO) {
        saveContributionService.execute(contributionRequestDTO);
    }

    @PostMapping(value = "/upload-file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FileUploadResponseDTO contributionFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String uploadTitle) {
        return uploadFileContributionService.execute(file, uploadTitle);
    }

    @GetMapping(value = "/{contributionId}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> getContributionFile(
            @PathVariable(value = "contributionId") String contributionId,
            @RequestParam(value = "requestUserId") UUID requestUserId
    ) {
        System.out.println(contributionId);
        return getFileContributionService.execute(contributionId, requestUserId);
    }

    @GetMapping(value = "/{contributionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContributionDetailResponseDTO getContribution(
            @PathVariable(value = "contributionId") String contributionId
    ) {
        return getContributionService.execute(contributionId);
    }

}
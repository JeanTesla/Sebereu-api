package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.response.UserResponseDTO;
import com.nosferatu.Sebereuapi.service.auth.GetProfileImageService;
import com.nosferatu.Sebereuapi.service.auth.UploadProfileImageService;
import com.nosferatu.Sebereuapi.service.user.GetUserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final GetUserService getUserService;

    private final UploadProfileImageService uploadProfileImageService;

    private final GetProfileImageService getProfileImageService;

    public UserController(GetUserService getUserService, UploadProfileImageService uploadProfileImageService, GetProfileImageService getProfileImageService) {
        this.getUserService = getUserService;
        this.uploadProfileImageService = uploadProfileImageService;
        this.getProfileImageService = getProfileImageService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO getUser(
            @PathVariable(value = "userId") UUID userId
    ) {
        return getUserService.execute(userId);
    }

    @GetMapping(value = "/{userId}/profile-image",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> getProfileImage(@PathVariable(value = "userId") UUID userId) {
        return getProfileImageService.execute(userId);
    }

    @PostMapping(value = "/{userId}/upload-profile-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void profileImageUpload(
            @RequestParam("file") MultipartFile file,
            @PathVariable(value = "userId") UUID userId) {
        uploadProfileImageService.execute(file, userId);
    }
}

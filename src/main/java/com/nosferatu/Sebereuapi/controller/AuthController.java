package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.request.SignInRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.request.SignUpRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.SignInResponseDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.SignUpResponseDTO;
import com.nosferatu.Sebereuapi.service.auth.GetProfileImageService;
import com.nosferatu.Sebereuapi.service.auth.SignInService;
import com.nosferatu.Sebereuapi.service.auth.SignUpService;
import com.nosferatu.Sebereuapi.service.auth.UploadProfileImageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final SignInService signInService;

    private final SignUpService signUpService;



    public AuthController(
            SignInService signInService,
            SignUpService signUpService) {
        this.signInService = signInService;
        this.signUpService = signUpService;
    }

    @PostMapping("/sign-in")
    public SignInResponseDTO doSignIn(@RequestBody @Valid SignInRequestDTO signInRequestDTO) {
        return signInService.execute(signInRequestDTO);
    }

    @PostMapping("/sign-up")
    public SignUpResponseDTO doSignUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return signUpService.execute(signUpRequestDTO);
    }

}

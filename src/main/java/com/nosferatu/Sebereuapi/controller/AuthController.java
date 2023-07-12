package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.request.SignInRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.request.SignUpRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.SignInResponseDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.SignUpResponseDTO;
import com.nosferatu.Sebereuapi.service.auth.SignInService;
import com.nosferatu.Sebereuapi.service.auth.SignUpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/sign-in")
    public SignInResponseDTO doSignIn(@RequestBody SignInRequestDTO signInRequestDTO) {
        return signInService.execute(signInRequestDTO);
    }

    @GetMapping("/sign-up")
    public SignUpResponseDTO doSignUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return signUpService.execute(signUpRequestDTO);
    }
}

package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.model.dto.request.LoginRequestDTO;
import com.nosferatu.Sebereuapi.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public boolean doLogin(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        return loginService.exec(loginRequestDTO);
    }
}

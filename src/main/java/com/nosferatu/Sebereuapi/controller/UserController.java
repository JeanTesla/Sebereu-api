package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.response.UserResponseDTO;
import com.nosferatu.Sebereuapi.service.user.GetUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO getUser(
            @PathVariable(value = "userId") UUID userId
    ) {
        return getUserService.execute(userId);
    }
}

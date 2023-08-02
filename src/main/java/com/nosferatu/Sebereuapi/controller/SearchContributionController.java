package com.nosferatu.Sebereuapi.controller;

import com.nosferatu.Sebereuapi.domain.dto.response.ContributionDetailResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.service.contribution.*;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchContributionController {

    private final SearchContributionService searchContributionService;

    public SearchContributionController(
            SearchContributionService searchContributionService) {

        this.searchContributionService = searchContributionService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ContributionDetailResponseDTO> search(
            @RequestParam(value = "filter") String filter
    ) {
        return searchContributionService.execute(filter);
    }
}

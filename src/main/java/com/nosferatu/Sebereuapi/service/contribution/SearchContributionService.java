package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.response.ContributionDetailResponseDTO;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchContributionService {

    private final ContributionRepository contributionRepository;

    public SearchContributionService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public Page<ContributionDetailResponseDTO> execute(String filter, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return contributionRepository.findByTitleOrArtistUsingCustomQuery(filter, pageRequest)
                .map(ContributionDetailResponseDTO::fromContributionEntity);

    }
}

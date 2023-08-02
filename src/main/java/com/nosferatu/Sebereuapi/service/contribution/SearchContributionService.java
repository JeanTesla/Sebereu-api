package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.response.ContributionDetailResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchContributionService {

    private final ContributionRepository contributionRepository;

    public SearchContributionService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public Page<ContributionDetailResponseDTO> execute(String filter) {
        Pageable p = Pageable.unpaged();

        return contributionRepository.findByTitleOrArtistUsingCustomQuery(filter, p)
                .map(ContributionDetailResponseDTO::fromContributionEntity);

    }
}

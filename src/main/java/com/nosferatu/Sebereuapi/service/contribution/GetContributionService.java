package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.response.ContributionDetailResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.exception.ContributionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetContributionService {

    private final ContributionRepository contributionRepository;

    public GetContributionService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public ContributionDetailResponseDTO execute(UUID contributionId){
        Contribution contribution = contributionRepository.findById(contributionId)
                .orElseThrow(ContributionNotFoundException::new);

        return ContributionDetailResponseDTO.fromContributionEntity(contribution);
    }
}

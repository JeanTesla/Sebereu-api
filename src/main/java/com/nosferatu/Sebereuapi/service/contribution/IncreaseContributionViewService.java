package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.response.ContributionDetailResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.exception.ContributionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IncreaseContributionViewService {

    private final ContributionRepository contributionRepository;

    public IncreaseContributionViewService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public void execute(String contributionId) {
        Contribution contribution = contributionRepository.findById(contributionId)
                .orElseThrow(ContributionNotFoundException::new);

        Long views = contribution.getViews();
        Long aux;

        if (Objects.nonNull(views)) aux = ++views;
        else aux = 1L;

        contribution.setViews(aux);

        contributionRepository.save(contribution);
    }
}

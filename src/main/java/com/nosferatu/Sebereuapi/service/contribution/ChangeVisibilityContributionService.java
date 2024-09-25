package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.request.ChangeContributionVisibilityRequestDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.ContributionNotFoundException;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChangeVisibilityContributionService {

    private final ContributionRepository contributionRepository;

    private final UserRepository userRepository;

    public ChangeVisibilityContributionService(
            ContributionRepository contributionRepository,
            UserRepository userRepository
    ) {
        this.contributionRepository = contributionRepository;
        this.userRepository = userRepository;
    }

    public void execute(UUID contributionId, ChangeContributionVisibilityRequestDTO changeContributionVisibilityRequestDTO){

        UUID userId = changeContributionVisibilityRequestDTO.getUserId();

        Contribution contribution = contributionRepository.findByIdAndUserId(contributionId, userId)
                .orElseThrow(ContributionNotFoundException::new);

        contribution.setIsVisible(!contribution.getIsVisible());

        contributionRepository.save(contribution);
    }
}

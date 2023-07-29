package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.response.ContributionResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetAllContributionService {

    private final ContributionRepository contributionRepository;

    private final UserRepository userRepository;

    public GetAllContributionService(
            ContributionRepository contributionRepository,
            UserRepository userRepository
    ) {
        this.contributionRepository = contributionRepository;
        this.userRepository = userRepository;
    }

    public List<ContributionResponseDTO> execute(UUID userId){

        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<Contribution> contributions = contributionRepository.findByUserId(userId).get();

        return contributions
                .stream()
                .map(ContributionResponseDTO::fromContributionEntity)
                .collect(Collectors.toList());
    }
}


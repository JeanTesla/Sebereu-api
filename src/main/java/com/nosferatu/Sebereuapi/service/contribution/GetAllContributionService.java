package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<Contribution> execute(){
        return contributionRepository.findByUserId(
                UUID.fromString("cf19a853-1353-45ed-ba08-9a13f0657a77")
        ).orElseThrow();
    }
}


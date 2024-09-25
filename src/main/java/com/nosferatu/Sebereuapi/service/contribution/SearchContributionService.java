package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.response.ContributionDetailResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchContributionService {

    private final ContributionRepository contributionRepository;

    public SearchContributionService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public Page<ContributionDetailResponseDTO> execute(String filter, Integer page, Integer size) {

        filter = Normalizer.normalize(filter  , Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toLowerCase();

        Pageable pageRequest = PageRequest.of(page, size);
        Page<Contribution> contributionPage = contributionRepository
                .findByNormalizedIndex(filter, pageRequest);

        List<ContributionDetailResponseDTO> contributionList = contributionPage.stream()
                .map(ContributionDetailResponseDTO::fromContributionEntity)
                .collect(Collectors.toList());

        return new PageImpl<>(contributionList, contributionPage.getPageable() , contributionPage.getTotalElements());
    }
}

package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UploadAlreadyAssociatedException;
import com.nosferatu.Sebereuapi.exception.UploadNotFoundException;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SaveContributionService {

    private final ContributionRepository contributionRepository;

    private final FileUploadRepository fileUploadRepository;

    private final UserRepository userRepository;

    public SaveContributionService(
            ContributionRepository contributionRepository,
            FileUploadRepository fileUploadRepository,
            UserRepository userRepository
    ) {
        this.contributionRepository = contributionRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.userRepository = userRepository;
    }

    public void execute(ContributionRequestDTO contributionRequestDTO) {

        fileUploadRepository.findById(contributionRequestDTO.getUploadId())
                .orElseThrow(UploadNotFoundException::new);

        userRepository.findById(contributionRequestDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if(contributionRepository.findByUploadId(contributionRequestDTO.getUploadId()).isPresent()){
            throw new UploadAlreadyAssociatedException();
        }

        contributionRepository.save(contributionRequestDTO.toEntity());
    }
}

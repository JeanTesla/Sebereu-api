package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
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

    public void exec(ContributionRequestDTO contributionRequestDTO) {
        /* TODO Além de verificar se o uploadId existe, também será necessário
        *   criar uma lógica que impeça que um mesmo uploadId seja usado em mais de uma contribuição */

        userRepository.findById(contributionRequestDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);

        fileUploadRepository.findById(contributionRequestDTO.getUploadId())
                        .orElseThrow(UploadNotFoundException::new);

        contributionRepository.save(contributionRequestDTO.toEntity());
    }
}

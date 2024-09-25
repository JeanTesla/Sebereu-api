package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UploadAlreadyAssociatedException;
import com.nosferatu.Sebereuapi.exception.UploadNotFoundException;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;

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

        // Verifica se já existe alguma contribuição com o uploadId que está vindo da request
        if(contributionRepository.findByUploadId(contributionRequestDTO.getUploadId()).isPresent()){
            throw new UploadAlreadyAssociatedException();
        }

        Contribution toSaveContribution = contributionRequestDTO.toEntity();
        toSaveContribution.setIsVisible(true);
        toSaveContribution.setViews(0L);

        //Cria um indice normalizado para que a pesquisa aconteça com mais eficiencia
        String[] words = new String[]{
            toSaveContribution.getTitle(),
            toSaveContribution.getComposer()
        };

        toSaveContribution.setNormalizedIndex(concatAndNormalizeStrings(words));

        contributionRepository.save(toSaveContribution);
    }

    private String concatAndNormalizeStrings(String[] words){
        String normalizedWords = String.join(" ", words).toLowerCase();
        return Normalizer.normalize(normalizedWords  , Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}

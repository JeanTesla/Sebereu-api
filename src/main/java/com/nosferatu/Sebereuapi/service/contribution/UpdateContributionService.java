package com.nosferatu.Sebereuapi.service.contribution;

import com.nosferatu.Sebereuapi.domain.dto.request.ContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.request.UpdateContributionRequestDTO;
import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.repository.ContributionRepository;
import com.nosferatu.Sebereuapi.domain.repository.FileUploadRepository;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.ContributionNotFoundException;
import com.nosferatu.Sebereuapi.exception.UploadAlreadyAssociatedException;
import com.nosferatu.Sebereuapi.exception.UploadNotFoundException;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.UUID;

@Service
public class UpdateContributionService {

    private final ContributionRepository contributionRepository;

    private final FileUploadRepository fileUploadRepository;

    private final UserRepository userRepository;

    public UpdateContributionService(
            ContributionRepository contributionRepository,
            FileUploadRepository fileUploadRepository,
            UserRepository userRepository
    ) {
        this.contributionRepository = contributionRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.userRepository = userRepository;
    }

    public void execute(UUID contributionId, UpdateContributionRequestDTO updateContributionRequestDTO) {

        if(updateContributionRequestDTO.getUploadId() != null){
            fileUploadRepository.findById(updateContributionRequestDTO.getUploadId())
                    .orElseThrow(UploadNotFoundException::new);

            if(contributionRepository.findByUploadId(updateContributionRequestDTO.getUploadId()).isPresent()){
                throw new UploadAlreadyAssociatedException();
            }
        }

        userRepository.findById(updateContributionRequestDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Contribution contributionToEdit = contributionRepository.findById(contributionId)
                .orElseThrow(ContributionNotFoundException::new);

        Contribution contributionEditedData = updateContributionRequestDTO.toEntity();

        contributionToEdit.setTitle(contributionEditedData.getTitle());
        contributionToEdit.setComposer(contributionEditedData.getComposer());
        contributionToEdit.setArrangement(contributionEditedData.getArrangement());
        contributionToEdit.setDescription(contributionEditedData.getDescription());
        contributionToEdit.setGenrePicker(contributionEditedData.getGenrePicker());
        contributionToEdit.setInstrumentPicker(contributionEditedData.getInstrumentPicker());

        if(contributionEditedData.getUploadId() != null){
            UUID currentUploadId = contributionToEdit.getUploadId();

            contributionToEdit.setUploadId(contributionEditedData.getUploadId());
        }

        String[] words = new String[]{
                contributionEditedData.getTitle(),
                contributionEditedData.getComposer()
        };

        contributionToEdit.setNormalizedIndex(concatAndNormalizeStrings(words));

        contributionRepository.save(contributionToEdit);
    }

    private String concatAndNormalizeStrings(String[] words){
        String normalizedWords = String.join(" ", words).toLowerCase();
        return Normalizer.normalize(normalizedWords  , Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}

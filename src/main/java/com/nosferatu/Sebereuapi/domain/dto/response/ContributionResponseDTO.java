package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class ContributionResponseDTO {

    private UUID contributionId;

    private String title;

    private String composer;

    private Boolean isVisible;

    private Long views;

    private Date createdAt;

    public static ContributionResponseDTO fromContributionEntity(Contribution contribution){
        return ContributionResponseDTO.builder()
                .contributionId(contribution.getId())
                .title(contribution.getTitle())
                .composer(contribution.getComposer())
                .isVisible(contribution.getIsVisible())
                .views(contribution.getViews())
                .createdAt(contribution.getCreatedAt())
                .build();
    }
}



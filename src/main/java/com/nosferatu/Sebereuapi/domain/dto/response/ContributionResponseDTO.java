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

    private String contributionId;

    private String title;

    private String artist;

    private Date createdAt;

    public static ContributionResponseDTO fromContributionEntity(Contribution contribution){
        return ContributionResponseDTO.builder()
                .contributionId(contribution.getId())
                .title(contribution.getTitle())
                .artist(contribution.getArtist())
                .createdAt(contribution.getCreatedAt())
                .build();
    }
}



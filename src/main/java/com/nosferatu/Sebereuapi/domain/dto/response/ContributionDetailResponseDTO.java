package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.enumerated.MusicalGenreEnum;
import com.nosferatu.Sebereuapi.domain.enumerated.SheetTypeEnum;
import com.nosferatu.Sebereuapi.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class ContributionDetailResponseDTO {

    private UUID contributionId;

    private UUID userId;

    private String title;

    private String composer;

    private String arrangement;

    private String description;

    private MusicalGenreEnum musicalGenre;

    private SheetTypeEnum sheetType;

    private List<String> genrePicker;

    private List<String> instrumentPicker;

    private Long views;

    private Date createdAt;

    public static ContributionDetailResponseDTO fromContributionEntity(Contribution contribution){
        return ContributionDetailResponseDTO.builder()
                .contributionId(contribution.getId())
                .userId(contribution.getUserId())
                .title(contribution.getTitle())
                .composer(contribution.getComposer())
                .arrangement(contribution.getArrangement())
                .description(contribution.getDescription())
                .genrePicker(Utils.stringToStringList(contribution.getGenrePicker()))
                .instrumentPicker(Utils.stringToStringList(contribution.getInstrumentPicker()))
                .views(contribution.getViews())
                .createdAt(contribution.getCreatedAt())
                .build();
    }



}

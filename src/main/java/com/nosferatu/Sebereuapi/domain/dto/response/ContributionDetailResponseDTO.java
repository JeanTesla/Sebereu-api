package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.enumerated.MusicalGenreEnum;
import com.nosferatu.Sebereuapi.domain.enumerated.SheetTypeEnum;
import com.nosferatu.Sebereuapi.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class ContributionDetailResponseDTO {

    private String contributionId;

    private String title;

    private String artist;

    private String arrangement;

    private String description;

    private MusicalGenreEnum musicalGenre;

    private SheetTypeEnum sheetType;

    private List<String> genrePicker;

    private List<String> instrumentPicker;

    private Timestamp createdAt;

    public static ContributionDetailResponseDTO fromContributionEntity(Contribution contribution){
        return ContributionDetailResponseDTO.builder()
                .contributionId(contribution.getId())
                .title(contribution.getTitle())
                .artist(contribution.getArtist())
                .arrangement(contribution.getArrangement())
                .description(contribution.getDescription())
                .musicalGenre(contribution.getMusicalGenre())
                .sheetType(contribution.getSheetType())
                .genrePicker(Utils.stringToStringList(contribution.getGenrePicker()))
                .instrumentPicker(Utils.stringToStringList(contribution.getInstrumentPicker()))
                .createdAt(contribution.getCreatedAt())
                .build();
    }



}

package com.nosferatu.Sebereuapi.domain.dto.request;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import com.nosferatu.Sebereuapi.domain.enumerated.MusicalGenreEnum;
import com.nosferatu.Sebereuapi.domain.enumerated.SheetTypeEnum;
import com.nosferatu.Sebereuapi.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ContributionRequestDTO {

    private UUID userId;

    private UUID uploadId;

    private String title;

    private String artist;

    private String arrangement;

    private String description;

    private MusicalGenreEnum musicalGenre;

    private SheetTypeEnum sheetType;

    private List<String> genrePicker;

    private List<String> instrumentPicker;

    public Contribution toEntity(){

        return Contribution.builder()
                .userId(this.userId)
                .uploadId(this.uploadId)
                .title(this.title)
                .artist(this.artist)
                .arrangement(this.arrangement)
                .description(this.description)
                .musicalGenre(this.musicalGenre)
                .sheetType(this.sheetType)
                .genrePicker(Utils.stringListToString(this.genrePicker))
                .instrumentPicker(Utils.stringListToString(this.instrumentPicker))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

}

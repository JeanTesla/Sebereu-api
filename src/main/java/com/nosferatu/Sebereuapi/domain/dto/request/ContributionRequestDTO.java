package com.nosferatu.Sebereuapi.domain.dto.request;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ContributionRequestDTO {

    private UUID userId;

    private UUID uploadId;

    private String title;

    private String description;

    private List<String> tags;

    public String getTagsInString(){
        return String.join(";", tags);
    }

    public Contribution toEntity(){
        return Contribution.builder()
                .userId(this.userId)
                .uploadId(this.uploadId)
                .title(this.title)
                .description(this.description)
                .tags(this.getTagsInString())
                .build();
    }
}

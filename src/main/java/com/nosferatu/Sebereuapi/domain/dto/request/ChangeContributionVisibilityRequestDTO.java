package com.nosferatu.Sebereuapi.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangeContributionVisibilityRequestDTO {
    private UUID userId;
}

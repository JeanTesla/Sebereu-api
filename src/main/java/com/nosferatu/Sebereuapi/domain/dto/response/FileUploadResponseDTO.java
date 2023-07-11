package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FileUploadResponseDTO {
    private Long uploadId;

    public static FileUploadResponseDTO fromEntity(FileUpload fileUpload){
        return FileUploadResponseDTO.builder().uploadId(fileUpload.getFileUploadId()).build();
    }
}

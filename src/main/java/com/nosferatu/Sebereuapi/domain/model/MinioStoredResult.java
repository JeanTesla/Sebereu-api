package com.nosferatu.Sebereuapi.domain.model;

import io.minio.ObjectWriteResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MinioStoredResult {

    private String storedPath;
    private String storedFileName;
    private String storedFullFilePath;
    private ObjectWriteResponse objectWriteResponse;
}

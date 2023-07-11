package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}

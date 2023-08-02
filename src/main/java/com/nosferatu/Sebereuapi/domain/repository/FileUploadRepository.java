package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, UUID> {
}

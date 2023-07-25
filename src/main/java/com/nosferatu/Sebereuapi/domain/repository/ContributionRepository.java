package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContributionRepository extends JpaRepository<Contribution, UUID> {

    Optional<Contribution> findByUploadId(UUID uploadId);

    Optional<List<Contribution>> findByUserId(UUID userId);
}

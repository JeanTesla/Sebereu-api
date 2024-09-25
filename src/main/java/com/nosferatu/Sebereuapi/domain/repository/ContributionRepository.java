package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContributionRepository extends PagingAndSortingRepository<Contribution, UUID> {

    Optional<Contribution> findByUploadId(UUID uploadId);

    Optional<Contribution> findByIdAndUserId(UUID contributionId, UUID userId);

    Page<Contribution> findByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);

    @Query(value = "select tc from Contribution tc \n" +
            "where tc.normalizedIndex like %?1%\n" +
            "and tc.isVisible IS true\n" +
            "order by tc.createdAt desc")
    Page<Contribution> findByNormalizedIndex(String filter, Pageable pageable);
}
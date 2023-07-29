package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//public interface ContributionRepository extends JpaRepository<Contribution, UUID> {
//
//    Optional<Contribution> findByUploadId(UUID uploadId);
//
//    Optional<ArrayList<Contribution>> findByUserId(UUID userId);
//}

public interface ContributionRepository extends ElasticsearchRepository<Contribution, String> {

    Optional<Contribution> findByUploadId(UUID uploadId);

    Optional<ArrayList<Contribution>> findByUserId(UUID userId);
}


package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
}

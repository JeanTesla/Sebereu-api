package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.Contribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ContributionRepository extends ElasticsearchRepository<Contribution, String> {

    Optional<Contribution> findByUploadId(UUID uploadId);

    Optional<ArrayList<Contribution>> findByUserId(UUID userId);


    List<Contribution> findByTitleContaining(String title);

    @Query("{\"bool\": {\n" +
            "\t\t\t\"must\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"multi_match\": {\n" +
            "\t\t\t\t\t\t\"query\": \"?0\",\n" +
            "\t\t\t\t\t\t\"zero_terms_query\": \"all\",\n" +
            "\t\t\t\t\t\t\"analyzer\": \"standard\",\n" +
            "\t\t\t\t\t\t\"fields\": [\"title\", \"artist\"],\n" +
            "\t\t\t\t\t\t\"minimum_should_match\":\"99%\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t}}")
    Page<Contribution> findByTitleOrArtistUsingCustomQuery(String filter, Pageable pageable);
}
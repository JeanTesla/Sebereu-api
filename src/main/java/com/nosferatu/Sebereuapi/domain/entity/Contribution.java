package com.nosferatu.Sebereuapi.domain.entity;

import com.nosferatu.Sebereuapi.domain.enumerated.MusicalGenreEnum;
import com.nosferatu.Sebereuapi.domain.enumerated.SheetTypeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName="contribution")
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private UUID userId;

    private UUID uploadId;

    private String title;

    private String artist;

    private String arrangement;

    private String description;

    private MusicalGenreEnum musicalGenre;

    private SheetTypeEnum sheetType;

    private String genrePicker;

    private String instrumentPicker;

    private Long views;

    private Date createdAt;

    private Date updatedAt;

}

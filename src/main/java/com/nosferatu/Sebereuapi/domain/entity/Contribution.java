package com.nosferatu.Sebereuapi.domain.entity;

import com.nosferatu.Sebereuapi.domain.enumerated.MusicalGenreEnum;
import com.nosferatu.Sebereuapi.domain.enumerated.SheetTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


//@Entity
//@Table(name = "tbl_contributions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(indexName="contribution")
public class Contribution {

    //@Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    //private UUID contributionId;

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

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}

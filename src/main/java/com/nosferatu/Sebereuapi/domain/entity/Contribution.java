package com.nosferatu.Sebereuapi.domain.entity;

import com.nosferatu.Sebereuapi.domain.enumerated.MusicalGenreEnum;
import com.nosferatu.Sebereuapi.domain.enumerated.SheetTypeEnum;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_contributions")
public class Contribution {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private UUID userId;

    private UUID uploadId;

    private String title;

    private String composer;

    private String arrangement;

    private String description;

    private String genrePicker;

    private String instrumentPicker;

    private Long views;

    private String normalizedIndex;

    private Boolean isVisible;

    private Date createdAt;

    private Date updatedAt;

}

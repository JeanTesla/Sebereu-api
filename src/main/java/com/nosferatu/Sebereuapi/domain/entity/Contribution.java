package com.nosferatu.Sebereuapi.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_contributions")
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contributionId;
}

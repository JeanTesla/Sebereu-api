package com.nosferatu.Sebereuapi.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(
        name = "tbl_users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UniqueUserNameAndEmail", columnNames = { "userName", "email" })
        })
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID userId;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String city;

    private String address;

    private String state;

    private String postalCode;

    private String aboutMe;

    private String quickMessage;

    private UUID profileImageUploadId;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
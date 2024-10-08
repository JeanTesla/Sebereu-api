package com.nosferatu.Sebereuapi.domain.repository;

import com.nosferatu.Sebereuapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserId(UUID userId);
}

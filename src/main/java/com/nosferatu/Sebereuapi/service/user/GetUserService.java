package com.nosferatu.Sebereuapi.service.user;

import com.nosferatu.Sebereuapi.domain.dto.response.UserResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserService {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO execute(UUID userId){
        User user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserResponseDTO.fromUserEntity(user);
    }
}

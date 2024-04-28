package com.nosferatu.Sebereuapi.service.user;

import com.nosferatu.Sebereuapi.domain.dto.response.UserRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.UserResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUserService {
    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId, UserRequestDTO userRequestDTO){
        User user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        userRepository.save(userRequestDTO.toUserEntity(user));
    }
}

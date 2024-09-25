package com.nosferatu.Sebereuapi.service.user;

import com.nosferatu.Sebereuapi.domain.dto.response.UserRequestDTO;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserEmailAlreadyExistsException;
import com.nosferatu.Sebereuapi.exception.UserNameAlreadyExistsException;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UpdateUserService {
    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId, UserRequestDTO userRequestDTO){
        User savedUser = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        if(!Objects.equals(savedUser.getUserName(), userRequestDTO.getUserName())){
            if(userRepository.findByUserName(userRequestDTO.getUserName()).isPresent()){
                throw new UserNameAlreadyExistsException();
            }
        }

        if(!Objects.equals(savedUser.getEmail(), userRequestDTO.getEmail())){
            if(userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()){
                throw new UserEmailAlreadyExistsException();
            }
        }

        userRepository.save(userRequestDTO.toUserEntity(savedUser));
    }
}

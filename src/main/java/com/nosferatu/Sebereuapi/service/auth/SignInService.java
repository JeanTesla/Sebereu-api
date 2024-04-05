package com.nosferatu.Sebereuapi.service.auth;

import com.nosferatu.Sebereuapi.domain.dto.response.SignInResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.dto.request.SignInRequestDTO;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    private final UserRepository userRepository;

    public SignInService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignInResponseDTO execute(SignInRequestDTO signInRequestDTO) {
        User user = userRepository.findByEmailAndPassword(
                        signInRequestDTO.getEmail(),
                        signInRequestDTO.getPassword())
                .orElseThrow(UserNotFoundException::new);

        return SignInResponseDTO.fromUserEntity(user);
    }
}

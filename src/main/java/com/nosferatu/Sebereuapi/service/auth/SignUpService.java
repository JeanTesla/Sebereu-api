package com.nosferatu.Sebereuapi.service.auth;

import com.nosferatu.Sebereuapi.domain.dto.request.SignUpRequestDTO;
import com.nosferatu.Sebereuapi.domain.dto.response.SignUpResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserEmailAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignUpResponseDTO execute(SignUpRequestDTO signUpRequestDTO) {
        // TODO se atentar ao fato de que deve ter uma verificação de math de senha lá em signInRequestDTO

        if(userRepository.findByEmail(signUpRequestDTO.getEmail()).isPresent()){
            throw new UserEmailAlreadyExistsException();
        }

        User savedUser = userRepository.save(signUpRequestDTO.toEntity());

        return SignUpResponseDTO.fromUserEntity(savedUser);
    }
}

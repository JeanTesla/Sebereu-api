package com.nosferatu.Sebereuapi.service.auth;

import com.nosferatu.Sebereuapi.domain.dto.response.LoginResponseDTO;
import com.nosferatu.Sebereuapi.domain.entity.User;
import com.nosferatu.Sebereuapi.domain.dto.request.LoginRequestDTO;
import com.nosferatu.Sebereuapi.domain.repository.UserRepository;
import com.nosferatu.Sebereuapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDTO execute(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmailAndPassword(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword())
                .orElseThrow(UserNotFoundException::new);

        return LoginResponseDTO.fromUserEntity(user);
    }
}

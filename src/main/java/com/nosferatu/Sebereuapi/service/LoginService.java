package com.nosferatu.Sebereuapi.service;

import com.nosferatu.Sebereuapi.entitie.User;
import com.nosferatu.Sebereuapi.model.dto.request.LoginRequestDTO;
import com.nosferatu.Sebereuapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean exec(LoginRequestDTO loginRequestDTO) throws Exception {
        Optional<User> userOpt = userRepository.findByEmailAndPassword(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );

        if(userOpt.isEmpty()) throw new Exception("Nenhum usuário encontrado");

        return true;
    }
}

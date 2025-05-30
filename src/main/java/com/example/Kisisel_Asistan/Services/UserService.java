package com.example.Kisisel_Asistan.Services;

import com.example.Kisisel_Asistan.Dto.UserDTO;
import com.example.Kisisel_Asistan.Dto.UserMapper;
import com.example.Kisisel_Asistan.Entity.User;
import com.example.Kisisel_Asistan.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return UserMapper.toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}

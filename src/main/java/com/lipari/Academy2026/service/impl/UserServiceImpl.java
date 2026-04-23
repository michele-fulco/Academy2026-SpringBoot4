package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.entity.ERole;
import com.lipari.Academy2026.entity.Role;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.exceptions.BadRequestException;
import com.lipari.Academy2026.exceptions.ResourceNotFoundException;
import com.lipari.Academy2026.mapper.UserMapper;
import com.lipari.Academy2026.payload.request.SignupRequest;
import com.lipari.Academy2026.repository.RoleRepository;
import com.lipari.Academy2026.repository.UserRepository;
import com.lipari.Academy2026.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Transactional
    @Override
    public UserDTO newUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new BadRequestException("Errore: Username già in uso!");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("Errore: Email già in uso!");
        }

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Errore: Ruolo ROLE_USER non trovato."));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        UserEntity user = UserEntity.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .password(encoder.encode(userDTO.getPassword()))
                .active(true)
                .creationDate(LocalDateTime.now())
                .roles(roles)
                .build();

        user = this.userRepository.save(user);
        return this.userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserDTO registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        UserEntity user = UserEntity.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .active(true)
                .creationDate(LocalDateTime.now())
                .roles(roles)
                .build();

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public boolean isAdmin(String id) {
        return userRepository.findById(Long.parseLong(id))
                .map(u -> u.getRoles().stream()
                        .anyMatch(r -> r.getName() == ERole.ROLE_ADMIN))
                .orElse(false);
    }

    @Override
    public UserDTO getUser(String id) {
        return this.userRepository.findById(Long.parseLong(id))
                .map(this.userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Utente con id " + id + " non trovato"));
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> list = this.userRepository.findAll();
        return this.userMapper.toDtoList(list);
    }
}

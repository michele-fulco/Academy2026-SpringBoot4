package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.mapper.UserMapper;
import com.lipari.Academy2026.entity.ERole;
import com.lipari.Academy2026.entity.Role;
import com.lipari.Academy2026.repository.RoleRepository;
import com.lipari.Academy2026.repository.UserRepository;
import com.lipari.Academy2026.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public UserDTO newUser(UserDTO userDTO) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Errore: Ruolo ROLE_USER non trovato."));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        UserEntity user = UserEntity.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .password(userDTO.getPassword())
                .active(true)
                .creationDate(LocalDateTime.now())
                .roles(roles)
                .build();

        user = this.userRepository.save(user);
        return this.userMapper.toDto(user);
    }

    @Override
    public boolean isAdmin(String id) {
        return userRepository.findById(Long.parseLong(id))
                .map(u -> u.getRoles().stream()
                        .anyMatch(r -> r.getName() == ERole.ROLE_ADMIN))
                .orElse(false);
    }

    public UserDTO getUser(String id) throws Exception {

        Optional<UserEntity> op = this.userRepository.findById(Long.parseLong(id));
        if (op.isPresent()) {
            return this.userMapper.toDto(op.get());
        } else {
            throw new Exception("utente non trovato");
        }

    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> list = this.userRepository.findAll();
        return this.userMapper.toDtoList(list);
    }
}


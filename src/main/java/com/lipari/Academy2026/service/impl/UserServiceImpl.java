package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.mapper.UserMapper;
import com.lipari.Academy2026.repository.UserRepository;
import com.lipari.Academy2026.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO newUser(String name, String surname, String password, String email, String username) {
        UserEntity user = UserEntity.builder()
                .email(email)
                .active(true)
                .creationDate(LocalDateTime.now())
                .lastLogin(null)
                .roles(new HashSet<>()) // Dovrai gestire l'assegnazione dei ruoli reali qui
                .username(username)
                .name(name)
                .surname(surname)
                .password(password)
                .build();
        user = this.userRepository.save(user);
        return this.userMapper.toDto(user);

    }

    @Override
    public boolean isAdmin(String id) {
        Optional<UserEntity> user = this.userRepository.findById(Long.parseLong(id));
        return user.map(userEntity -> userEntity.getRoles().stream()
                .anyMatch(role -> role.getName().name().equals("ROLE_ADMIN"))).orElse(false);
    }
    public UserDTO getUser(String id) throws Exception {

        Optional<UserEntity> op = this.userRepository.findById(Long.parseLong(id));
        if(op.isPresent()) {
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


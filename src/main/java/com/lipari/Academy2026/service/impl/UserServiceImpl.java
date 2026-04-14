package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.mapper.UserMapper;
import com.lipari.Academy2026.repository.UserRepositry;
import com.lipari.Academy2026.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepositry userRepositry;
    private final UserMapper userMapper;

    @Override
    public UserDTO newUser(String name, String surname, String password, String email, String username) {
        UserEntity user = new UserEntity().builder()
                .email(email)
                .active(true)
                .creationDate(LocalDateTime.now())
                .lastLogin(null)
                .role("UTENTE")
                .username(username)
                .name(name)
                .surname(surname)
                .password(password)
                .build();
        user = this.userRepositry.save(user);
        return this.userMapper.toDto(user);

    }

    @Override
    public boolean isAdmin(String id) {
        Optional<UserEntity> user = this.userRepositry.findById(id);
        return user.map(userEntity -> userEntity.getRole().equals("ADMIN")).orElse(false);
        /*Optional<UserEntity> user = this.userRepositry.findById(id);
        if (user.isPresent()){
            return user.get().getRole().equals("ADMIN");
        }
        return false;*/
    }
    public UserDTO getUser(String id) throws Exception {

        Optional<UserEntity> op = this.userRepositry.findById(id);
        if(op.isPresent()) {
            return this.userMapper.toDto(op.get());
        } else {
            throw new Exception("utente non trovato");
        }

    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> list = this.userRepositry.findAll();
        return this.userMapper.toDtoList(list);
    }
}


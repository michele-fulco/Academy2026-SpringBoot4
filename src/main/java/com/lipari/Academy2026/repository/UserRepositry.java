package com.lipari.Academy2026.repository;

import com.lipari.Academy2026.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<UserEntity,String> {
    UserEntity findByName(String name);
}

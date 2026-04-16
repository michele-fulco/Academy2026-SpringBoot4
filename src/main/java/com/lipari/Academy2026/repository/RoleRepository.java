package com.lipari.Academy2026.repository;

import com.lipari.Academy2026.model.ERole;
import com.lipari.Academy2026.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}

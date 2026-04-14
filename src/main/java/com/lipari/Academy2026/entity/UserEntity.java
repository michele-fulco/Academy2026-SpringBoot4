package com.lipari.Academy2026.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String surname;
    String role;
    String password;
    String email;
    Boolean active;
    String username;
    LocalDateTime creationDate;
    LocalDateTime lastLogin;
    /*@OneToMany(mappedBy = "user")
    private List<ProductEntity> products;*/

}

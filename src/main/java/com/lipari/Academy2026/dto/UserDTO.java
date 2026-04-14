package com.lipari.Academy2026.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UserDTO {
    String id;
    String name;
    String surname;
    String role;
    String password;
    String username;
    String email;
}

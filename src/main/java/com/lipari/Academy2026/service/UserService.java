package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.payload.request.SignupRequest;

import java.util.List;

public interface UserService {
    UserDTO newUser(UserDTO userDTO);

    UserDTO registerUser(SignupRequest signUpRequest);

    boolean isAdmin(String id);

    List<UserDTO> getUsers();

    UserDTO getUser(Long id);
    UserDTO updateUser(UserDTO userDTO);
    void softDelete(Long id);
}

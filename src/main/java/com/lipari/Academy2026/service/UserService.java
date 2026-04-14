package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO newUser(String name, String surname, String password, String email, String username);

    public boolean isAdmin(String id);

    public List<UserDTO> getUsers();

    public UserDTO getUser(String id) throws Exception;
}

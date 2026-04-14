package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.service.CategoryService;
import com.lipari.Academy2026.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/isAdmin/{id}")
    public boolean isAdmin(@PathVariable String id) {
        return this.userService.isAdmin(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        try {
            return new ResponseEntity<>(this.userService.getUser(id),HttpStatus.OK);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@GetMapping("/currentUser")
    public ResponseEntity<UserDTO> currentUser()*/
    @PostMapping("/new")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(this.userService.newUser(userDTO.getName(), userDTO.getSurname(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

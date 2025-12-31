package com.tolbargy.controller;

import com.tolbargy.model.User;
import com.tolbargy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<User> me() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Integer id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

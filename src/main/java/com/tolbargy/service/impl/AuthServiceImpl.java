package com.tolbargy.service.impl;

import com.tolbargy.dtos.LoginRequest;
import com.tolbargy.dtos.RegisterRequest;
import com.tolbargy.dtos.TokenResponse;
import com.tolbargy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.tolbargy.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse register(RegisterRequest request) {
        var user = User.builder()
                .usuario(request.usuario())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.usuario(),
                        request.password()
                )
        );

        var user = userRepository.findByUsuario(request.usuario())
                .orElseThrow();

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse refreshToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Bearer token");
        }

        var refreshToken = authHeader.substring(7);
        var username = jwtService.extractUsername(refreshToken);

        var user = userRepository.findByUsuario(username)
                .orElseThrow();

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        var newAccessToken = jwtService.generateToken(user);
        return new TokenResponse(newAccessToken, refreshToken);
    }
}

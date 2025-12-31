package com.tolbargy.config;

import com.tolbargy.model.User;
import com.tolbargy.repository.UserRepository;
import com.tolbargy.service.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1️⃣ Ignorar endpoints públicos
        if (request.getServletPath().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Leer Authorization header
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3️⃣ Extraer JWT
        final String jwtToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(jwtToken);

        // 4️⃣ Validaciones básicas
        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 5️⃣ Cargar usuario
        User user = userRepository.findByUsuario(username).orElse(null);
        if (user == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 6️⃣ Validar token
        if (!jwtService.isTokenValid(jwtToken, user)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 7️⃣ Autenticar en el contexto de Spring Security
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 8️⃣ Continuar cadena
        filterChain.doFilter(request, response);
    }
}

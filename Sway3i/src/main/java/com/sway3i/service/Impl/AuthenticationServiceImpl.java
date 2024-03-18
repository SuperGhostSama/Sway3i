package com.sway3i.service.Impl;

import com.sway3i.dto.Authentication.Request.AuthenticationRequest;
import com.sway3i.dto.Authentication.Request.RefreshTokenRequestDTO;
import com.sway3i.dto.Authentication.Request.RegisterRequest;
import com.sway3i.dto.Authentication.Response.AuthenticationResponse;
import com.sway3i.dto.Authentication.Response.RefreshTokenResponseDTO;
import com.sway3i.entities.Role;
import com.sway3i.entities.User;
import com.sway3i.repository.RoleRepository;
import com.sway3i.repository.UserRepository;
import com.sway3i.security.JwtService;
import com.sway3i.service.AuthenticationService;
import com.sway3i.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }

        // Determine the role based on the request
        Role defaultRole = roleService.findDefaultRole().orElse(null);

        String requestedRole = request.getRole().toUpperCase(); // Convert to uppercase for case-insensitive comparison

        Role userRole;
        if ("TEACHER".equals(requestedRole)) {
            userRole = roleRepository.findByName("ROLE_TEACHER").orElse(defaultRole);
        } else if ("STUDENT".equals(requestedRole)) {
            userRole = roleRepository.findByName("ROLE_STUDENT").orElse(defaultRole);
        } else {
            throw new IllegalArgumentException("Invalid role specified");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .city(request.getCity())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .isValid("STUDENT".equals(requestedRole)) // Set isValid based on role
                .build();

        userRepository.save(user);

        // Generate and save refresh token
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .city(user.getCity())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        // Generate access token and refresh token
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Set the refresh token in the Member entity
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return AuthenticationResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .city(user.getCity())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        // Extract username from the refresh token
        String username = jwtService.extractUserName(refreshToken);

        // Find the user by username
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        // Verify if the stored refresh token matches the provided one
        if (!Objects.equals(user.getRefreshToken(), refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        // Verify if the refresh token is expired
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Expired refresh token");
        }

        // Generate a new access token
        String newAccessToken = jwtService.generateToken(user);

        // Expire the old refresh token and generate a new one
        String newRefreshToken = jwtService.generateRefreshToken(user);

        // Update the refresh token in the Member entity
        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        // Create and return the response DTO
        return RefreshTokenResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }


}
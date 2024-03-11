package com.sway3i.service;

import com.sway3i.dto.Authentication.Request.AuthenticationRequest;
import com.sway3i.dto.Authentication.Request.RefreshTokenRequestDTO;
import com.sway3i.dto.Authentication.Request.RegisterRequest;
import com.sway3i.dto.Authentication.Response.AuthenticationResponse;
import com.sway3i.dto.Authentication.Response.RefreshTokenResponseDTO;


public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest user);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest);

}


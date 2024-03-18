package com.sway3i.dto.Authentication.Response;

import com.sway3i.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String refreshToken;
    private long userId;
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private Role role;
    private Boolean isValid;
}

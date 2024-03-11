package com.sway3i.dto.Role.Response;

import com.sway3i.entities.Authority;
import com.sway3i.entities.Role;

import java.util.List;


public record RoleResponseDTO(
        String name,
        List<String> authorities,
        boolean isDefault
) {
    public static RoleResponseDTO fromRole(Role role){
        return new RoleResponseDTO(
                role.getName(),
                role.getAuthorities().stream().map(Authority::getName).toList(),
                role.isDefault()
        );
    }
}


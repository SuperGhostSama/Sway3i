package com.sway3i.dto.Role.Request;

import com.sway3i.entities.Authority;
import com.sway3i.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RoleRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotNull(message = "Authorities cannot be null")
        List<Authority> authorities,
        boolean isDefault
){
    public Role toRole(){
        return Role.builder()
                .name(name)
                .isDefault(isDefault)
                .authorities(authorities)
                .build();
    }
}
package com.sway3i.dto.Role.Request;

import com.sway3i.entities.Authority;
import com.sway3i.entities.Role;

import java.util.List;

public record RoleRequestDTO(
        String name,
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
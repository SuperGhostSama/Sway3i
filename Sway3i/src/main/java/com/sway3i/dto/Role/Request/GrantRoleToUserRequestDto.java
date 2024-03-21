package com.sway3i.dto.Role.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrantRoleToUserRequestDto {
    @NotNull(message = "Role ID cannot be null")
    private Long roleId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}


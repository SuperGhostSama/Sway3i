package com.sway3i.dto.Role.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrantAuthoritiesRequestDto {
    Long authorityId;
    Long roleId;
}


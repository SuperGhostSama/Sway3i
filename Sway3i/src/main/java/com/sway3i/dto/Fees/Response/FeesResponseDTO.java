package com.sway3i.dto.Fees.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeesResponseDTO {
    private Long id;
    private String name;
    private int percentage;
}
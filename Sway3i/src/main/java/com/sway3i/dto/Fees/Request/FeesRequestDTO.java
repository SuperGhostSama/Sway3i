package com.sway3i.dto.Fees.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeesRequestDTO {
    private String name;
    private int percentage;
}

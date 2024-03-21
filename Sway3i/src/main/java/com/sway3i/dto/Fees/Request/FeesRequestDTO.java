package com.sway3i.dto.Fees.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeesRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Percentage must be positive")
    private int percentage;
}

package com.sway3i.dto.Program.Request;

import com.sway3i.entities.enums.Days;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramRequestDTO {
    @NotNull(message = "Day cannot be null")
    private Days day;

    @NotNull(message = "Time cannot be null")
    private LocalTime time;
}

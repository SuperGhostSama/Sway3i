package com.sway3i.dto.Program.Response;

import com.sway3i.entities.enums.Days;
import lombok.*;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramResponseDTO {
    private Long id;
    private Days day;
    private LocalTime time;
}

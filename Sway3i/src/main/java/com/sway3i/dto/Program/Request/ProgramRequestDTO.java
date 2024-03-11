package com.sway3i.dto.Program.Request;

import com.sway3i.entities.enums.Days;
import lombok.*;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramRequestDTO {
    private Days day;
    private LocalTime time;
}

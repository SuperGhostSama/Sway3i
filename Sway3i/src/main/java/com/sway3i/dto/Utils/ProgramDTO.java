package com.sway3i.dto.Utils;

import com.sway3i.entities.enums.Days;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDTO {
    private Long id;
    private Days date;
    private LocalTime time;

}
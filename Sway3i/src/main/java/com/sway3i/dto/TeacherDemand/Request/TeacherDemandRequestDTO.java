package com.sway3i.dto.TeacherDemand.Request;

import com.sway3i.entities.enums.DemandStatus;
import com.sway3i.entities.enums.EducationLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDemandRequestDTO {
    @NotNull(message = "Created by ID cannot be null")
    private Long createdById;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @NotNull(message = "Education level cannot be null")
    private EducationLevel educationLevel;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Demand status cannot be null")
    private DemandStatus status;
}

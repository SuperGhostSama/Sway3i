package com.sway3i.dto.TeacherDemand.Request;

import com.sway3i.entities.User;
import com.sway3i.entities.enums.DemandStatus;
import com.sway3i.entities.enums.EducationLevel;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDemandRequestDTO {
    private long createdById;
    private String subject;
    private EducationLevel educationLevel;
    private String description;
    private DemandStatus status;
}

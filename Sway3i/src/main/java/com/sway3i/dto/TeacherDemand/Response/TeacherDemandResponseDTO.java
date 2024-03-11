package com.sway3i.dto.TeacherDemand.Response;

import com.sway3i.entities.User;
import com.sway3i.entities.enums.DemandStatus;
import com.sway3i.entities.enums.EducationLevel;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDemandResponseDTO {
    private Long id;
    private User createdBy;
    private String subject;
    private EducationLevel educationLevel;
    private String description;
    private DemandStatus status;

}

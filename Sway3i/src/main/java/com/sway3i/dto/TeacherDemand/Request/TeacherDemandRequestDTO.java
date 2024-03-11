package com.sway3i.dto.TeacherDemand.Request;

import com.sway3i.entities.User;
import com.sway3i.entities.enums.EducationLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherDemandRequestDTO {
    private User createdBy;
    private String subject;
    private EducationLevel educationLevel;
    private String description;
}

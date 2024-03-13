package com.sway3i.dto.StudentsInCourse.Response;

import com.sway3i.entities.enums.PricingPlan;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentsInCourseResponseDTO {
    private Long id;
    private LocalDate createdAt;
    private boolean isExpired;
    private Long studentId;
    private Long courseId;
    private PricingPlan pricingPlan;
}

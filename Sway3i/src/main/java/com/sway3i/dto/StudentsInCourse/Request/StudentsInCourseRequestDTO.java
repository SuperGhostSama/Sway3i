package com.sway3i.dto.StudentsInCourse.Request;

import com.sway3i.entities.enums.PricingPlan;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentsInCourseRequestDTO {
    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Pricing plan cannot be null")
    private PricingPlan pricingPlan;
}

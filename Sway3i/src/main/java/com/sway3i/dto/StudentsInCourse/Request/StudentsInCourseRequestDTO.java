package com.sway3i.dto.StudentsInCourse.Request;

import com.sway3i.entities.enums.PricingPlan;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentsInCourseRequestDTO {
    private Long studentId;
    private Long courseId;
    private PricingPlan pricingPlan;
}

package com.sway3i.dto.StudentsInCourse.Response;

import com.sway3i.entities.Course;
import com.sway3i.entities.enums.PricingPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrolledCourseResponseDTO {
    private Long id;
    private LocalDate createdAt;
    private boolean isExpired;
    private Long studentId;
    private Long courseId;
    private String courseDetails;
    private String courseSubject;
    private String createdByFirstName;
    private String createdByLastName;
    private String createdByCity;
    private PricingPlan pricingPlan;
}

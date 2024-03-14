package com.sway3i.dto.Course.Request;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    private LocalDate createdAt;
    private Long createdByUserId;
    private String subject;
    private String courseDetails;
    private String courseIsFor;
    private long price;
    private String city;
    private EducationLevel educationLevel;
    private CourseType type;
    private int maxStudents;
    private List<Long> programIds;

}

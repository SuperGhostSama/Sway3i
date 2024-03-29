package com.sway3i.dto.Course.Response;

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
public class CourseResponseDTO {

    private Long id;
    private LocalDate createdAt;
    private Long createdByUserId;
    private String subject;
    private String courseName;
    private String courseDetails;
    private String courseIsFor;
    private long price;
    private String city;
    private String educationLevel;
    private String type;
    private int maxStudents;
    private List<Long> programIds;
    private String link;
    private String address;
}

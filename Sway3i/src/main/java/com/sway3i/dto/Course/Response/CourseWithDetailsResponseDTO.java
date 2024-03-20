package com.sway3i.dto.Course.Response;

import com.sway3i.dto.Utils.ProgramDTO;
import com.sway3i.dto.Utils.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseWithDetailsResponseDTO {

    private Long id;
    private LocalDate createdAt;
    private UserDTO createdByUser;
    private String courseName;
    private String subject;
    private String courseDetails;
    private String courseIsFor;
    private long price;
    private String city;
    private String educationLevel;
    private String type;
    private int maxStudents;
    private List<ProgramDTO> programs;

    private String link;
    private String address;

}
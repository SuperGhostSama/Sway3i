package com.sway3i.dto.Course.Request;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.EducationLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "CreatedByUserId cannot be null")
    private Long createdByUserId;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @NotBlank(message = "CourseName cannot be blank")
    private String courseName;

    @NotBlank(message = "CourseDetails cannot be blank")
    private String courseDetails;

    @NotBlank(message = "CourseIsFor cannot be blank")
    private String courseIsFor;

    @Positive(message = "Price must be positive")
    private long price;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotNull(message = "EducationLevel cannot be null")
    private EducationLevel educationLevel;

    @NotNull(message = "Type cannot be null")
    private CourseType type;

    @Positive(message = "MaxStudents must be positive")
    private int maxStudents;

    @NotNull(message = "ProgramIds cannot be null")
    @Size(min = 1, message = "At least one program must be selected")
    private List<Long> programIds;

    private String link;

    private String address;
}

package com.sway3i.entities;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    private String subject;
    private String courseDetails;
    private String courseIsFor;
    private long price;
    private String city;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private CourseType type;

    private int maxStudents;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "course_program",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id"))
    private List<Program> programs;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Fees> fees;

    private String link;

    private String address;
}

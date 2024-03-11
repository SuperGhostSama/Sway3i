package com.sway3i.entities;

import com.sway3i.entities.enums.DemandStatus;
import com.sway3i.entities.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class TeacherDemand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    private String subject;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    private String description;

    @Enumerated(EnumType.STRING)
    private DemandStatus status;
}

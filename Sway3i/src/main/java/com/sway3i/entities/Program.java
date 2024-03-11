package com.sway3i.entities;

import com.sway3i.entities.enums.Days;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Days day;

    private LocalTime time;

    @ManyToMany(mappedBy = "programs")
    private List<Course> courses;
}


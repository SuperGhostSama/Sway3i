package com.sway3i.service.Impl;

import com.sway3i.dto.StudentsInCourse.Request.StudentsInCourseRequestDTO;
import com.sway3i.dto.StudentsInCourse.Response.StudentsInCourseResponseDTO;
import com.sway3i.entities.StudentsInCourse;
import com.sway3i.repository.CourseRepository;
import com.sway3i.repository.StudentsInCourseRepository;
import com.sway3i.repository.UserRepository;
import com.sway3i.service.StudentsInCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsInCourseServiceImpl implements StudentsInCourseService {

    private final StudentsInCourseRepository studentsInCourseRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<StudentsInCourseResponseDTO> getAllStudentsInCourses() {
        List<StudentsInCourse> studentsInCourses = studentsInCourseRepository.findAll();
        return studentsInCourses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentsInCourseResponseDTO> getStudentsInCourseById(Long id) {
        return studentsInCourseRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public StudentsInCourseResponseDTO createStudentsInCourse(StudentsInCourseRequestDTO studentsInCourseRequest) {
        StudentsInCourse studentsInCourse = convertToEntity(studentsInCourseRequest);
        StudentsInCourse savedStudentsInCourse = studentsInCourseRepository.save(studentsInCourse);
        return convertToDTO(savedStudentsInCourse);
    }

    @Override
    public StudentsInCourseResponseDTO updateStudentsInCourse(Long id, StudentsInCourseRequestDTO updatedStudentsInCourseRequest) {
        Optional<StudentsInCourse> existingStudentsInCourse = studentsInCourseRepository.findById(id);
        if (existingStudentsInCourse.isPresent()) {
            StudentsInCourse updatedStudentsInCourse = convertToEntity(updatedStudentsInCourseRequest);
            updatedStudentsInCourse.setId(id);
            StudentsInCourse savedStudentsInCourse = studentsInCourseRepository.save(updatedStudentsInCourse);
            return convertToDTO(savedStudentsInCourse);
        } else {
            throw new RuntimeException("StudentsInCourse not found with id: " + id);
        }
    }

    @Override
    public void deleteStudentsInCourse(Long id) {
        studentsInCourseRepository.deleteById(id);
    }

    private StudentsInCourseResponseDTO convertToDTO(StudentsInCourse studentsInCourse) {
        return StudentsInCourseResponseDTO.builder()
                .id(studentsInCourse.getId())
                .createdAt(studentsInCourse.getCreatedAt())
                .isExpired(studentsInCourse.isExpired())
                .studentId(studentsInCourse.getStudent().getId())
                .courseId(studentsInCourse.getCourse().getId())
                .pricingPlan(studentsInCourse.getPricingPlan())
                .build();
    }

    private StudentsInCourse convertToEntity(StudentsInCourseRequestDTO studentsInCourseRequest) {
        return StudentsInCourse.builder()
                .createdAt(LocalDate.now())
                .isExpired(studentsInCourseRequest.isExpired())
                .student(userRepository.getUserById(studentsInCourseRequest.getStudentId()))
                .course(courseRepository.findById(studentsInCourseRequest.getCourseId())
                        .orElseThrow(() -> new RuntimeException("Course not found with id: " + studentsInCourseRequest.getCourseId())))
                .pricingPlan(studentsInCourseRequest.getPricingPlan())
                .build();
    }

}

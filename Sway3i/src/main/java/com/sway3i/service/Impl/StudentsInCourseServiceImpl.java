package com.sway3i.service.Impl;

import com.sway3i.dto.StudentsInCourse.Request.StudentsInCourseRequestDTO;
import com.sway3i.dto.StudentsInCourse.Response.EnrolledCourseResponseDTO;
import com.sway3i.dto.StudentsInCourse.Response.StudentsInCourseResponseDTO;
import com.sway3i.entities.Course;
import com.sway3i.entities.StudentsInCourse;
import com.sway3i.entities.User;
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

    @Override
    public List<EnrolledCourseResponseDTO> getEnrolledCoursesByStudentId(Long studentId) {
        List<StudentsInCourse> studentsInCourses = studentsInCourseRepository.findByStudentId(studentId);
        return studentsInCourses.stream()
                .map(studentsInCourse -> EnrolledCourseResponseDTO.builder()
                        .id(studentsInCourse.getId())
                        .createdAt(studentsInCourse.getCreatedAt())
                        .isExpired(studentsInCourse.isExpired())
                        .studentId(studentsInCourse.getStudent().getId())
                        .courseId(studentsInCourse.getCourse().getId())
                        .courseName(studentsInCourse.getCourse().getCourseName())
                        .courseDetails(studentsInCourse.getCourse().getCourseDetails())
                        .courseSubject(studentsInCourse.getCourse().getSubject())
                        .createdByFirstName(studentsInCourse.getCourse().getCreatedBy().getFirstName())
                        .createdByLastName(studentsInCourse.getCourse().getCreatedBy().getLastName())
                        .createdByCity(studentsInCourse.getCourse().getCreatedBy().getCity())
                        .pricingPlan(studentsInCourse.getPricingPlan())
                        .address(studentsInCourse.getCourse().getAddress())
                        .link(studentsInCourse.getCourse().getLink())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public boolean isStudentEnrolled(Long userId, Long courseId) {
        return studentsInCourseRepository.isStudentEnrolled(userId, courseId);
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
        User user = userRepository.getUserById(studentsInCourseRequest.getStudentId());

        if (user == null) {
            throw new RuntimeException("User not found with id: " + studentsInCourseRequest.getStudentId());
        }

        Course course = courseRepository.findById(studentsInCourseRequest.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + studentsInCourseRequest.getCourseId()));

        // Check if there is already a StudentsInCourse for the same user and course
        boolean isExistingCourseForUser = studentsInCourseRepository.existsByStudentAndCourse(user, course);
        if (isExistingCourseForUser) {
            throw new RuntimeException("Student already payed for this course");
        }

        return StudentsInCourse.builder()
                .createdAt(LocalDate.now())
                .isExpired(false)
                .student(user)
                .course(course)
                .pricingPlan(studentsInCourseRequest.getPricingPlan())
                .build();
    }




}

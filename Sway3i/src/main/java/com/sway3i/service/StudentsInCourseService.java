package com.sway3i.service;

import com.sway3i.dto.StudentsInCourse.Request.StudentsInCourseRequestDTO;
import com.sway3i.dto.StudentsInCourse.Response.EnrolledCourseResponseDTO;
import com.sway3i.dto.StudentsInCourse.Response.StudentsInCourseResponseDTO;
import com.sway3i.entities.Course;
import com.sway3i.entities.StudentsInCourse;

import java.util.List;
import java.util.Optional;

public interface StudentsInCourseService {

    List<StudentsInCourseResponseDTO> getAllStudentsInCourses();

    Optional<StudentsInCourseResponseDTO> getStudentsInCourseById(Long id);

    StudentsInCourseResponseDTO createStudentsInCourse(StudentsInCourseRequestDTO studentsInCourseRequest);

    StudentsInCourseResponseDTO updateStudentsInCourse(Long id, StudentsInCourseRequestDTO updatedStudentsInCourseRequest);

    void deleteStudentsInCourse(Long id);

    List<EnrolledCourseResponseDTO> getEnrolledCoursesByStudentId(Long studentId);

    Optional<Course> isStudentEnrolled(Long userId, Long courseId);

}

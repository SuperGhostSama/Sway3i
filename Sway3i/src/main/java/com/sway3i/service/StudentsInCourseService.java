package com.sway3i.service;

import com.sway3i.entities.StudentsInCourse;

import java.util.List;
import java.util.Optional;

public interface StudentsInCourseService {

    List<StudentsInCourse> getAllStudentsInCourses();
    Optional<StudentsInCourse> getStudentsInCourseById(Long id);
    StudentsInCourse createStudentsInCourse(StudentsInCourse studentsInCourse);
    StudentsInCourse updateStudentsInCourse(Long id, StudentsInCourse updatedStudentsInCourse);
    void deleteStudentsInCourse(Long id);
}

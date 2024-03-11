package com.sway3i.service.Impl;

import com.sway3i.entities.StudentsInCourse;
import com.sway3i.repository.StudentsInCourseRepository;
import com.sway3i.service.StudentsInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsInCourseServiceImpl implements StudentsInCourseService {

    private final StudentsInCourseRepository studentsInCourseRepository;

    @Autowired
    public StudentsInCourseServiceImpl(StudentsInCourseRepository studentsInCourseRepository) {
        this.studentsInCourseRepository = studentsInCourseRepository;
    }

    @Override
    public List<StudentsInCourse> getAllStudentsInCourses() {
        return studentsInCourseRepository.findAll();
    }

    @Override
    public Optional<StudentsInCourse> getStudentsInCourseById(Long id) {
        return studentsInCourseRepository.findById(id);
    }

    @Override
    public StudentsInCourse createStudentsInCourse(StudentsInCourse studentsInCourse) {
        return studentsInCourseRepository.save(studentsInCourse);
    }

    @Override
    public StudentsInCourse updateStudentsInCourse(Long id, StudentsInCourse updatedStudentsInCourse) {
        Optional<StudentsInCourse> existingStudentsInCourse = studentsInCourseRepository.findById(id);
        if (existingStudentsInCourse.isPresent()) {
            updatedStudentsInCourse.setId(id);
            return studentsInCourseRepository.save(updatedStudentsInCourse);
        } else {
            throw new RuntimeException("StudentsInCourse not found with id: " + id);
        }
    }

    @Override
    public void deleteStudentsInCourse(Long id) {
        studentsInCourseRepository.deleteById(id);
    }
}

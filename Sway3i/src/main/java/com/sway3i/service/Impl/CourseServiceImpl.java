package com.sway3i.service.Impl;

import com.sway3i.entities.Course;
import com.sway3i.repository.CourseRepository;
import com.sway3i.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            updatedCourse.setId(id);
            return courseRepository.save(updatedCourse);
        } else {
            throw new RuntimeException("Course not found with id: " + id);
        }
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}

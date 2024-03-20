package com.sway3i.service;

import com.sway3i.dto.Course.Request.CourseRequestDTO;
import com.sway3i.dto.Course.Response.CourseDetailsResponseDTO;
import com.sway3i.dto.Course.Response.CourseResponseDTO;
import com.sway3i.dto.Course.Response.CourseWithDetailsResponseDTO;
import com.sway3i.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<CourseWithDetailsResponseDTO> getAllLatestCourses();
    Optional<CourseWithDetailsResponseDTO> getCourseById(Long id);
    CourseResponseDTO createCourse(CourseRequestDTO courseRequest);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO updatedCourseRequest);

    void deleteCourse(Long id);
    List<CourseWithDetailsResponseDTO> getAllCoursesByEmail(String email);

    CourseDetailsResponseDTO getCourseDetails(Long courseId);

    List<CourseWithDetailsResponseDTO> getAllCourses();
}

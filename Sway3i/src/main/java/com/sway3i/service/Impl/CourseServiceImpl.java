package com.sway3i.service.Impl;

import com.sway3i.dto.Course.Request.CourseRequestDTO;
import com.sway3i.dto.Course.Response.CourseResponseDTO;
import com.sway3i.entities.Course;
import com.sway3i.entities.Program;
import com.sway3i.repository.CourseRepository;
import com.sway3i.repository.UserRepository;
import com.sway3i.service.CourseService;
import com.sway3i.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    public CourseServiceImpl(CourseRepository courseRepository, UserService userService, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseResponseDTO> getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequest) {
        Course course = convertToEntity(courseRequest);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO updatedCourseRequest) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            Course updatedCourse = convertToEntity(updatedCourseRequest);
            updatedCourse.setId(id);
            Course savedCourse = courseRepository.save(updatedCourse);
            return convertToDTO(savedCourse);
        } else {
            throw new RuntimeException("Course not found with id: " + id);
        }
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseResponseDTO convertToDTO(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .createdAt(course.getCreatedAt())
                .createdByUserId(userRepository.getUserById(course.getCreatedBy().getId()).getId())
                .subject(course.getSubject())
                .courseDetails(course.getCourseDetails())
                .courseIsFor(course.getCourseIsFor())
                .price(course.getPrice())
                .city(course.getCity())
                .educationLevel(course.getEducationLevel().name())
                .type(course.getType().name())
                .studentsInPerson(course.getStudentsInPerson())
                .programId(course.getProgram() != null ? course.getProgram().getId() : null)
                .build();
    }

    private Course convertToEntity(CourseRequestDTO courseRequest) {
        return Course.builder()
                .createdAt(courseRequest.getCreatedAt())
                .createdBy(userRepository.getUserById(courseRequest.getCreatedByUserId()))
                .subject(courseRequest.getSubject())
                .courseDetails(courseRequest.getCourseDetails())
                .courseIsFor(courseRequest.getCourseIsFor())
                .price(courseRequest.getPrice())
                .city(courseRequest.getCity())
                .educationLevel(courseRequest.getEducationLevel())
                .type(courseRequest.getType())
                .studentsInPerson(courseRequest.getStudentsInPerson())
                .program(courseRequest.getProgramId() != null ? Program.builder().id(courseRequest.getProgramId()).build() : null)
                .build();
    }
}
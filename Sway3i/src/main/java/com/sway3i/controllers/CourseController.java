package com.sway3i.controllers;

import com.sway3i.dto.Course.Request.CourseRequestDTO;
import com.sway3i.dto.Course.Response.CourseDetailsResponseDTO;
import com.sway3i.dto.Course.Response.CourseResponseDTO;
import com.sway3i.dto.Course.Response.CourseWithDetailsResponseDTO;
import com.sway3i.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


//    @GetMapping
//    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
//        List<CourseResponseDTO> courses = courseService.getAllCourses();
//        return new ResponseEntity<>(courses, HttpStatus.OK);
//    }



    @GetMapping("/byEmail")
    public ResponseEntity<List<CourseWithDetailsResponseDTO>> getAllCoursesByEmail(@RequestParam String email) {
        List<CourseWithDetailsResponseDTO> courses = courseService.getAllCoursesByEmail(email);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}/details")
    public ResponseEntity<CourseDetailsResponseDTO> getCourseDetails(@PathVariable Long courseId) {
        CourseDetailsResponseDTO courseDetails = courseService.getCourseDetails(courseId);
        return ResponseEntity.ok(courseDetails);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequest) {
        CourseResponseDTO createdCourse = courseService.createCourse(courseRequest);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO updatedCourseRequest) {
        CourseResponseDTO updatedCourse = courseService.updateCourse(id, updatedCourseRequest);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/all")
    public List<CourseWithDetailsResponseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseWithDetailsResponseDTO> getCourseById(@PathVariable Long id) {
        Optional<CourseWithDetailsResponseDTO> course = courseService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<CourseWithDetailsResponseDTO>> getAllLatestCourses() {
        List<CourseWithDetailsResponseDTO> latestCourses = courseService.getAllLatestCourses();
        return ResponseEntity.ok(latestCourses);
    }
}

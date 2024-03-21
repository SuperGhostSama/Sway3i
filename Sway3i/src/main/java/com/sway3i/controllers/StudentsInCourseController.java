package com.sway3i.controllers;

import com.sway3i.dto.StudentsInCourse.Request.StudentsInCourseRequestDTO;
import com.sway3i.dto.StudentsInCourse.Response.EnrolledCourseResponseDTO;
import com.sway3i.dto.StudentsInCourse.Response.StudentsInCourseResponseDTO;
import com.sway3i.service.StudentsInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students-in-courses")
public class StudentsInCourseController {

    private final StudentsInCourseService studentsInCourseService;

    @Autowired
    public StudentsInCourseController(StudentsInCourseService studentsInCourseService) {
        this.studentsInCourseService = studentsInCourseService;
    }

    @GetMapping
    public ResponseEntity<List<StudentsInCourseResponseDTO>> getAllStudentsInCourses() {
        List<StudentsInCourseResponseDTO> studentsInCourses = studentsInCourseService.getAllStudentsInCourses();
        return new ResponseEntity<>(studentsInCourses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentsInCourseResponseDTO> getStudentsInCourseById(@PathVariable Long id) {
        Optional<StudentsInCourseResponseDTO> studentsInCourse = studentsInCourseService.getStudentsInCourseById(id);
        return studentsInCourse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<StudentsInCourseResponseDTO> createStudentsInCourse(@RequestBody StudentsInCourseRequestDTO studentsInCourseRequest) {
        StudentsInCourseResponseDTO createdStudentsInCourse = studentsInCourseService.createStudentsInCourse(studentsInCourseRequest);
        return new ResponseEntity<>(createdStudentsInCourse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentsInCourseResponseDTO> updateStudentsInCourse(@PathVariable Long id, @RequestBody StudentsInCourseRequestDTO updatedStudentsInCourseRequest) {
        StudentsInCourseResponseDTO updatedStudentsInCourseResponse = studentsInCourseService.updateStudentsInCourse(id, updatedStudentsInCourseRequest);
        return new ResponseEntity<>(updatedStudentsInCourseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentsInCourse(@PathVariable Long id) {
        studentsInCourseService.deleteStudentsInCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/enrolled-courses/{studentId}")
    public ResponseEntity<List<EnrolledCourseResponseDTO>> getEnrolledCoursesByStudentId(@PathVariable Long studentId) {
        List<EnrolledCourseResponseDTO> enrolledCourses = studentsInCourseService.getEnrolledCoursesByStudentId(studentId);
        return new ResponseEntity<>(enrolledCourses, HttpStatus.OK);
    }

    @GetMapping("/students/enrolled")
    public boolean isStudentEnrolled(
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId) {
        return studentsInCourseService.isStudentEnrolled(userId, courseId);
    }

}

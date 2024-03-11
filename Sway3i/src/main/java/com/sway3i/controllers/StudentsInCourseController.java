package com.sway3i.controllers;

import com.sway3i.entities.StudentsInCourse;
import com.sway3i.service.StudentsInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<StudentsInCourse>> getAllStudentsInCourses() {
        List<StudentsInCourse> studentsInCourses = studentsInCourseService.getAllStudentsInCourses();
        return new ResponseEntity<>(studentsInCourses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentsInCourse> getStudentsInCourseById(@PathVariable Long id) {
        Optional<StudentsInCourse> studentsInCourse = studentsInCourseService.getStudentsInCourseById(id);
        return studentsInCourse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StudentsInCourse> createStudentsInCourse(@RequestBody StudentsInCourse studentsInCourse) {
        StudentsInCourse createdStudentsInCourse = studentsInCourseService.createStudentsInCourse(studentsInCourse);
        return new ResponseEntity<>(createdStudentsInCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentsInCourse> updateStudentsInCourse(@PathVariable Long id, @RequestBody StudentsInCourse updatedStudentsInCourse) {
        StudentsInCourse updatedStudentsInCourseResponse = studentsInCourseService.updateStudentsInCourse(id, updatedStudentsInCourse);
        return new ResponseEntity<>(updatedStudentsInCourseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentsInCourse(@PathVariable Long id) {
        studentsInCourseService.deleteStudentsInCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.sway3i.controllers;

import com.sway3i.entities.TeacherDemand;
import com.sway3i.service.TeacherDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teacher-demands")
public class TeacherDemandController {

    private final TeacherDemandService teacherDemandService;

    @Autowired
    public TeacherDemandController(TeacherDemandService teacherDemandService) {
        this.teacherDemandService = teacherDemandService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDemand>> getAllTeacherDemands() {
        List<TeacherDemand> teacherDemands = teacherDemandService.getAllTeacherDemands();
        return new ResponseEntity<>(teacherDemands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDemand> getTeacherDemandById(@PathVariable Long id) {
        Optional<TeacherDemand> teacherDemand = teacherDemandService.getTeacherDemandById(id);
        return teacherDemand.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TeacherDemand> createTeacherDemand(@RequestBody TeacherDemand teacherDemand) {
        TeacherDemand createdTeacherDemand = teacherDemandService.createTeacherDemand(teacherDemand);
        return new ResponseEntity<>(createdTeacherDemand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDemand> updateTeacherDemand(@PathVariable Long id, @RequestBody TeacherDemand updatedTeacherDemand) {
        TeacherDemand updatedTeacherDemandResponse = teacherDemandService.updateTeacherDemand(id, updatedTeacherDemand);
        return new ResponseEntity<>(updatedTeacherDemandResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacherDemand(@PathVariable Long id) {
        teacherDemandService.deleteTeacherDemand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

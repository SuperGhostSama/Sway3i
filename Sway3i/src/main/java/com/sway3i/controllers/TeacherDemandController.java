package com.sway3i.controllers;

import com.sway3i.dto.TeacherDemand.Request.TeacherDemandRequestDTO;
import com.sway3i.dto.TeacherDemand.Response.TeacherDemandResponseDTO;
import com.sway3i.service.TeacherDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher-demands")
public class TeacherDemandController {

    private final TeacherDemandService teacherDemandService;

    @Autowired
    public TeacherDemandController(TeacherDemandService teacherDemandService) {
        this.teacherDemandService = teacherDemandService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDemandResponseDTO>> getAllTeacherDemands() {
        List<TeacherDemandResponseDTO> teacherDemands = teacherDemandService.getAllTeacherDemands();
        return ResponseEntity.ok(teacherDemands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDemandResponseDTO> getTeacherDemandById(@PathVariable Long id) {
        return teacherDemandService.getTeacherDemandById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeacherDemandResponseDTO> createTeacherDemand(@RequestBody TeacherDemandRequestDTO teacherDemandRequest) {
        TeacherDemandResponseDTO createdTeacherDemand = teacherDemandService.createTeacherDemand(teacherDemandRequest);
        return new ResponseEntity<>(createdTeacherDemand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDemandResponseDTO> updateTeacherDemand(
            @PathVariable Long id,
            @RequestBody TeacherDemandRequestDTO updatedTeacherDemandRequest
    ) {
        TeacherDemandResponseDTO updatedTeacherDemand = teacherDemandService.updateTeacherDemand(id, updatedTeacherDemandRequest);
        return ResponseEntity.ok(updatedTeacherDemand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacherDemand(@PathVariable Long id) {
        teacherDemandService.deleteTeacherDemand(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Void> acceptTeacherDemand(@PathVariable Long id) {
        teacherDemandService.acceptTeacherDemand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectTeacherDemand(@PathVariable Long id) {
        teacherDemandService.rejectTeacherDemand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

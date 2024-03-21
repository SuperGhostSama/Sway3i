package com.sway3i.controllers;

import com.sway3i.dto.Program.Request.ProgramRequestDTO;
import com.sway3i.dto.Program.Response.ProgramResponseDTO;
import com.sway3i.entities.Program;
import com.sway3i.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/programs")
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<List<ProgramResponseDTO>> getAllPrograms() {
        List<ProgramResponseDTO> programs = programService.getAllPrograms();
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponseDTO> getProgramById(@PathVariable Long id) {
        return programService.getProgramById(id)
                .map(program -> new ResponseEntity<>(program, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProgramResponseDTO> createProgram(@RequestBody ProgramRequestDTO programRequest) {
        try {
            ProgramResponseDTO createdProgram = programService.createProgram(programRequest);
            return new ResponseEntity<>(createdProgram, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponseDTO> updateProgram(@PathVariable Long id,
                                                            @RequestBody ProgramRequestDTO updatedProgramRequest) {
        try {
            ProgramResponseDTO updatedProgram = programService.updateProgram(id, updatedProgramRequest);
            return new ResponseEntity<>(updatedProgram, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

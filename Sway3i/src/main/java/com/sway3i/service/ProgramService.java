package com.sway3i.service;

import com.sway3i.entities.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    List<Program> getAllPrograms();
    Optional<Program> getProgramById(Long id);
    Program createProgram(Program program);
    Program updateProgram(Long id, Program updatedProgram);
    void deleteProgram(Long id);
}

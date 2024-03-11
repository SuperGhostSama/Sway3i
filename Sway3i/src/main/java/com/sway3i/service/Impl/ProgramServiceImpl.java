package com.sway3i.service.Impl;

import com.sway3i.entities.Program;
import com.sway3i.repository.ProgramRepository;
import com.sway3i.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Optional<Program> getProgramById(Long id) {
        return programRepository.findById(id);
    }

    @Override
    public Program createProgram(Program program) {
        return programRepository.save(program);
    }

    @Override
    public Program updateProgram(Long id, Program updatedProgram) {
        Optional<Program> existingProgram = programRepository.findById(id);
        if (existingProgram.isPresent()) {
            updatedProgram.setId(id);
            return programRepository.save(updatedProgram);
        } else {
            throw new RuntimeException("Program not found with id: " + id);
        }
    }

    @Override
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

}

package com.sway3i.service.Impl;

import com.sway3i.dto.Program.Request.ProgramRequestDTO;
import com.sway3i.dto.Program.Response.ProgramResponseDTO;
import com.sway3i.entities.Program;
import com.sway3i.repository.ProgramRepository;
import com.sway3i.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<ProgramResponseDTO> getAllPrograms() {
        List<Program> programs = programRepository.findAll();
        return programs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProgramResponseDTO> getProgramById(Long id) {
        return programRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public ProgramResponseDTO createProgram(ProgramRequestDTO programRequest) {
        // Check if a program with the same day and time already exists
        if (programRepository.existsByDayAndTime(programRequest.getDay(), programRequest.getTime())) {
            throw new RuntimeException("Program with the same day and time already exists");
        }

        Program program = convertToEntity(programRequest);
        Program savedProgram = programRepository.save(program);
        return convertToDTO(savedProgram);
    }


    @Override
    public ProgramResponseDTO updateProgram(Long id, ProgramRequestDTO updatedProgramRequest) {
        Optional<Program> existingProgram = programRepository.findById(id);
        if (existingProgram.isPresent()) {
            Program updatedProgram = convertToEntity(updatedProgramRequest);
            updatedProgram.setId(id);
            Program savedProgram = programRepository.save(updatedProgram);
            return convertToDTO(savedProgram);
        } else {
            throw new RuntimeException("Program not found with id: " + id);
        }
    }

    @Override
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    private ProgramResponseDTO convertToDTO(Program program) {
        return ProgramResponseDTO.builder()
                .id(program.getId())
                .day(program.getDay())
                .time(program.getTime())
                .build();
    }

    private Program convertToEntity(ProgramRequestDTO programRequest) {
        return Program.builder()
                .day(programRequest.getDay())
                .time(programRequest.getTime())
                .build();
    }
}

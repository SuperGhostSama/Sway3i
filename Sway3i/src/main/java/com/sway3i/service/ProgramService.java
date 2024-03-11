package com.sway3i.service;

import com.sway3i.dto.Program.Request.ProgramRequestDTO;
import com.sway3i.dto.Program.Response.ProgramResponseDTO;
import com.sway3i.entities.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {

    List<ProgramResponseDTO> getAllPrograms();

    Optional<ProgramResponseDTO> getProgramById(Long id);

    ProgramResponseDTO createProgram(ProgramRequestDTO programRequest);

    ProgramResponseDTO updateProgram(Long id, ProgramRequestDTO updatedProgramRequest);

    void deleteProgram(Long id);
}

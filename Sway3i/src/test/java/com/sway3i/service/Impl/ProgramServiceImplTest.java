package com.sway3i.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sway3i.dto.Program.Request.ProgramRequestDTO;
import com.sway3i.dto.Program.Response.ProgramResponseDTO;
import com.sway3i.entities.Program;
import com.sway3i.repository.ProgramRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramServiceImplTest {

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ProgramServiceImpl programService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPrograms() {
        List<Program> mockProgramList = new ArrayList<>();
        mockProgramList.add(new Program(1L, "Monday", "10:00"));
        mockProgramList.add(new Program(2L, "Tuesday", "11:00"));

        when(programRepository.findAll()).thenReturn(mockProgramList);

        List<ProgramResponseDTO> result = programService.getAllPrograms();

        assertEquals(mockProgramList.size(), result.size());
    }

    @Test
    public void testGetProgramById_WhenProgramFound() {
        Program mockProgram = new Program(1L, "Monday", "10:00");
        when(programRepository.findById(1L)).thenReturn(Optional.of(mockProgram));

        Optional<ProgramResponseDTO> result = programService.getProgramById(1L);

        assertTrue(result.isPresent());
        assertEquals(mockProgram.getDay(), result.get().getDay());
        assertEquals(mockProgram.getTime(), result.get().getTime());
    }

    @Test
    public void testGetProgramById_WhenProgramNotFound() {
        when(programRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProgramResponseDTO> result = programService.getProgramById(1L);

        assertFalse(result.isPresent());
    }


    @Test
    public void testDeleteProgram() {
        assertDoesNotThrow(() -> programService.deleteProgram(1L));
    }
}

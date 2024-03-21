package com.sway3i.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sway3i.dto.TeacherDemand.Request.TeacherDemandRequestDTO;
import com.sway3i.dto.TeacherDemand.Response.TeacherDemandResponseDTO;
import com.sway3i.entities.TeacherDemand;
import com.sway3i.entities.User;
import com.sway3i.entities.enums.DemandStatus;
import com.sway3i.entities.enums.EducationLevel;
import com.sway3i.repository.TeacherDemandRepository;
import com.sway3i.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherDemandServiceImplTest {

    @Mock
    private TeacherDemandRepository teacherDemandRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TeacherDemandServiceImpl teacherDemandService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTeacherDemands() {
        List<TeacherDemand> mockTeacherDemandList = new ArrayList<>();
        mockTeacherDemandList.add(new TeacherDemand(1L, null, "Math", EducationLevel.HIGH_SCHOOL, "Description", DemandStatus.IN_PROGRESS));

        when(teacherDemandRepository.findAll()).thenReturn(mockTeacherDemandList);

        List<TeacherDemandResponseDTO> result = teacherDemandService.getAllTeacherDemands();

        assertEquals(mockTeacherDemandList.size(), result.size());
    }

    @Test
    public void testGetTeacherDemandById_WhenTeacherDemandFound() {
        TeacherDemand mockTeacherDemand = new TeacherDemand(1L, null, "Math", EducationLevel.HIGH_SCHOOL, "Description", DemandStatus.IN_PROGRESS);
        when(teacherDemandRepository.findById(1L)).thenReturn(Optional.of(mockTeacherDemand));

        Optional<TeacherDemandResponseDTO> result = teacherDemandService.getTeacherDemandById(1L);

        assertTrue(result.isPresent());
        assertEquals(mockTeacherDemand.getStatus(), result.get().getStatus());
    }

    @Test
    public void testGetTeacherDemandById_WhenTeacherDemandNotFound() {
        when(teacherDemandRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TeacherDemandResponseDTO> result = teacherDemandService.getTeacherDemandById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateTeacherDemand() {
        TeacherDemandRequestDTO mockTeacherDemandRequest = new TeacherDemandRequestDTO(1L, "Math", EducationLevel.HIGH_SCHOOL, "Description", DemandStatus.IN_PROGRESS);
        User mockUser = new User(1L, "John", "Doe", "john.doe@example.com", true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(teacherDemandRepository.save(any())).thenReturn(new TeacherDemand(1L, mockUser, "Math", EducationLevel.HIGH_SCHOOL, "Description", DemandStatus.IN_PROGRESS));

        TeacherDemandResponseDTO result = teacherDemandService.createTeacherDemand(mockTeacherDemandRequest);

        assertNotNull(result);
    }

    @Test
    public void testDeleteTeacherDemand() {
        assertDoesNotThrow(() -> teacherDemandService.deleteTeacherDemand(1L));
    }

}

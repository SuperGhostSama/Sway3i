package com.sway3i.service;

import com.sway3i.dto.TeacherDemand.Request.TeacherDemandRequestDTO;
import com.sway3i.dto.TeacherDemand.Response.TeacherDemandResponseDTO;
import com.sway3i.entities.TeacherDemand;

import java.util.List;
import java.util.Optional;

public interface TeacherDemandService {
    List<TeacherDemandResponseDTO> getAllTeacherDemands();

    Optional<TeacherDemandResponseDTO> getTeacherDemandById(Long id);

    TeacherDemandResponseDTO createTeacherDemand(TeacherDemandRequestDTO teacherDemandRequest);

    void deleteTeacherDemand(Long id);

    void acceptTeacherDemand(Long id);

    void rejectTeacherDemand(Long id);

    List<TeacherDemand> getTeacherDemandsByEmail(String email);
}

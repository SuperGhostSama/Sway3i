package com.sway3i.service;

import com.sway3i.dto.TeacherDemand.Request.TeacherDemandRequestDTO;
import com.sway3i.dto.TeacherDemand.Response.TeacherDemandResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TeacherDemandService {
    List<TeacherDemandResponseDTO> getAllTeacherDemands();

    Optional<TeacherDemandResponseDTO> getTeacherDemandById(Long id);

    TeacherDemandResponseDTO createTeacherDemand(TeacherDemandRequestDTO teacherDemandRequest);

    TeacherDemandResponseDTO updateTeacherDemand(Long id, TeacherDemandRequestDTO updatedTeacherDemandRequest);

    void deleteTeacherDemand(Long id);
}

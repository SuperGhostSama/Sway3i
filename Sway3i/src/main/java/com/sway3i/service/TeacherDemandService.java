package com.sway3i.service;

import com.sway3i.entities.TeacherDemand;

import java.util.List;
import java.util.Optional;

public interface TeacherDemandService {
    List<TeacherDemand> getAllTeacherDemands();
    Optional<TeacherDemand> getTeacherDemandById(Long id);
    TeacherDemand createTeacherDemand(TeacherDemand teacherDemand);
    TeacherDemand updateTeacherDemand(Long id, TeacherDemand updatedTeacherDemand);
    void deleteTeacherDemand(Long id);
}

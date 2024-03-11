package com.sway3i.service.Impl;

import com.sway3i.entities.TeacherDemand;
import com.sway3i.repository.TeacherDemandRepository;
import com.sway3i.service.TeacherDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherDemandServiceImpl implements TeacherDemandService {

    private final TeacherDemandRepository teacherDemandRepository;

    @Autowired
    public TeacherDemandServiceImpl(TeacherDemandRepository teacherDemandRepository) {
        this.teacherDemandRepository = teacherDemandRepository;
    }

    @Override
    public List<TeacherDemand> getAllTeacherDemands() {
        return teacherDemandRepository.findAll();
    }

    @Override
    public Optional<TeacherDemand> getTeacherDemandById(Long id) {
        return teacherDemandRepository.findById(id);
    }

    @Override
    public TeacherDemand createTeacherDemand(TeacherDemand teacherDemand) {
        return teacherDemandRepository.save(teacherDemand);
    }

    @Override
    public TeacherDemand updateTeacherDemand(Long id, TeacherDemand updatedTeacherDemand) {
        Optional<TeacherDemand> existingTeacherDemand = teacherDemandRepository.findById(id);
        if (existingTeacherDemand.isPresent()) {
            updatedTeacherDemand.setId(id);
            return teacherDemandRepository.save(updatedTeacherDemand);
        } else {
            throw new RuntimeException("TeacherDemand not found with id: " + id);
        }
    }

    @Override
    public void deleteTeacherDemand(Long id) {
        teacherDemandRepository.deleteById(id);
    }
}

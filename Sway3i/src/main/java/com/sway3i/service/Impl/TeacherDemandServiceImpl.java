package com.sway3i.service.Impl;

import com.sway3i.dto.TeacherDemand.Request.TeacherDemandRequestDTO;
import com.sway3i.dto.TeacherDemand.Response.TeacherDemandResponseDTO;
import com.sway3i.entities.TeacherDemand;
import com.sway3i.repository.TeacherDemandRepository;
import com.sway3i.service.TeacherDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherDemandServiceImpl implements TeacherDemandService {

    private final TeacherDemandRepository teacherDemandRepository;

    @Autowired
    public TeacherDemandServiceImpl(TeacherDemandRepository teacherDemandRepository) {
        this.teacherDemandRepository = teacherDemandRepository;
    }

    @Override
    public List<TeacherDemandResponseDTO> getAllTeacherDemands() {
        List<TeacherDemand> teacherDemands = teacherDemandRepository.findAll();
        return teacherDemands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TeacherDemandResponseDTO> getTeacherDemandById(Long id) {
        return teacherDemandRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public TeacherDemandResponseDTO createTeacherDemand(TeacherDemandRequestDTO teacherDemandRequest) {
        TeacherDemand teacherDemand = convertToEntity(teacherDemandRequest);
        TeacherDemand savedTeacherDemand = teacherDemandRepository.save(teacherDemand);
        return convertToDTO(savedTeacherDemand);
    }

    @Override
    public TeacherDemandResponseDTO updateTeacherDemand(Long id, TeacherDemandRequestDTO updatedTeacherDemandRequest) {
        Optional<TeacherDemand> existingTeacherDemand = teacherDemandRepository.findById(id);
        if (existingTeacherDemand.isPresent()) {
            TeacherDemand updatedTeacherDemand = convertToEntity(updatedTeacherDemandRequest);
            updatedTeacherDemand.setId(id);
            TeacherDemand savedTeacherDemand = teacherDemandRepository.save(updatedTeacherDemand);
            return convertToDTO(savedTeacherDemand);
        } else {
            throw new RuntimeException("TeacherDemand not found with id: " + id);
        }
    }

    @Override
    public void deleteTeacherDemand(Long id) {
        teacherDemandRepository.deleteById(id);
    }

    @Override
    public void acceptTeacherDemand(Long id) {
        Optional<TeacherDemand> existingTeacherDemand = teacherDemandRepository.findById(id);
        if (existingTeacherDemand.isPresent()) {
            TeacherDemand teacherDemand = existingTeacherDemand.get();
            teacherDemand.setIsAccepted(true);
            teacherDemandRepository.save(teacherDemand);
        } else {
            throw new RuntimeException("TeacherDemand not found with id: " + id);
        }
    }

    private TeacherDemandResponseDTO convertToDTO(TeacherDemand teacherDemand) {
        return TeacherDemandResponseDTO.builder()
                .id(teacherDemand.getId())
                .createdBy(teacherDemand.getCreatedBy())
                .subject(teacherDemand.getSubject())
                .educationLevel(teacherDemand.getEducationLevel())
                .description(teacherDemand.getDescription())
                .build();
    }

    private TeacherDemand convertToEntity(TeacherDemandRequestDTO teacherDemandRequest) {
        return TeacherDemand.builder()
                .createdBy(teacherDemandRequest.getCreatedBy())
                .subject(teacherDemandRequest.getSubject())
                .educationLevel(teacherDemandRequest.getEducationLevel())
                .description(teacherDemandRequest.getDescription())
                .build();
    }
}

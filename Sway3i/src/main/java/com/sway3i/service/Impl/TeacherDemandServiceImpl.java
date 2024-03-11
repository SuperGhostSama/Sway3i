package com.sway3i.service.Impl;

import com.sway3i.dto.TeacherDemand.Request.TeacherDemandRequestDTO;
import com.sway3i.dto.TeacherDemand.Response.TeacherDemandResponseDTO;
import com.sway3i.entities.TeacherDemand;
import com.sway3i.entities.User;
import com.sway3i.entities.enums.DemandStatus;
import com.sway3i.repository.TeacherDemandRepository;
import com.sway3i.repository.UserRepository;
import com.sway3i.service.TeacherDemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherDemandServiceImpl implements TeacherDemandService {

    private final TeacherDemandRepository teacherDemandRepository;
    private final UserRepository userRepository;


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
        User createdBy = userRepository.findById(teacherDemandRequest.getCreatedById())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + teacherDemandRequest.getCreatedById()));

        TeacherDemand teacherDemand = TeacherDemand.builder()
                .createdBy(createdBy)
                .subject(teacherDemandRequest.getSubject())
                .educationLevel(teacherDemandRequest.getEducationLevel())
                .description(teacherDemandRequest.getDescription())
                .status(DemandStatus.IN_PROGRESS)
                .build();

        TeacherDemand savedTeacherDemand = teacherDemandRepository.save(teacherDemand);
        return convertToDTO(savedTeacherDemand);
    }

    @Override
    public void deleteTeacherDemand(Long id) {
        teacherDemandRepository.deleteById(id);
    }

    @Override
    public void acceptTeacherDemand(Long id) {
        updateDemandStatus(id, DemandStatus.ACCEPTED);
    }

    @Override
    public void rejectTeacherDemand(Long id) {
        updateDemandStatus(id, DemandStatus.REJECTED);
    }

    //Helper methods
    private void updateDemandStatus(Long id, DemandStatus status) {
        Optional<TeacherDemand> existingTeacherDemand = teacherDemandRepository.findById(id);
        if (existingTeacherDemand.isPresent()) {
            TeacherDemand teacherDemand = existingTeacherDemand.get();
            teacherDemand.setStatus(status);
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
                .status(teacherDemand.getStatus())
                .build();
    }

}

package com.sway3i.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sway3i.dto.StudentsInCourse.Request.StudentsInCourseRequestDTO;
import com.sway3i.dto.StudentsInCourse.Response.StudentsInCourseResponseDTO;
import com.sway3i.entities.StudentsInCourse;
import com.sway3i.repository.StudentsInCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentsInCourseServiceImplTest {

    @Mock
    private StudentsInCourseRepository studentsInCourseRepository;

    @InjectMocks
    private StudentsInCourseServiceImpl studentsInCourseService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetStudentsInCourseById_WhenStudentsInCourseNotFound() {
        when(studentsInCourseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<StudentsInCourseResponseDTO> result = studentsInCourseService.getStudentsInCourseById(1L);

        assertFalse(result.isPresent());
    }


    @Test
    public void testUpdateStudentsInCourse_WhenStudentsInCourseNotExists() {
        StudentsInCourseRequestDTO mockUpdatedStudentsInCourseRequest = new StudentsInCourseRequestDTO(1L, 1L, null);

        when(studentsInCourseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> studentsInCourseService.updateStudentsInCourse(1L, mockUpdatedStudentsInCourseRequest));
    }

    @Test
    public void testDeleteStudentsInCourse() {
        assertDoesNotThrow(() -> studentsInCourseService.deleteStudentsInCourse(1L));
    }

}

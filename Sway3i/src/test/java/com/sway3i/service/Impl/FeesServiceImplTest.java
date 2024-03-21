package com.sway3i.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.sway3i.dto.Fees.Request.FeesRequestDTO;
import com.sway3i.dto.Fees.Response.FeesResponseDTO;
import com.sway3i.entities.Fees;
import com.sway3i.repository.FeesRepository;
import com.sway3i.service.Impl.FeesServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FeesServiceImplTest {

    @Mock
    private FeesRepository feesRepository;

    @InjectMocks
    private FeesServiceImpl feesService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllFees() {
        List<Fees> mockFeesList = new ArrayList<>();
        mockFeesList.add(new Fees(1L, "Fees1", 10));
        mockFeesList.add(new Fees(2L, "Fees2", 20));

        when(feesRepository.findAll()).thenReturn(mockFeesList);

        List<FeesResponseDTO> result = feesService.getAllFees();

        assertEquals(mockFeesList.size(), result.size());
    }

    @Test
    public void testGetFeesById() {
        Fees mockFees = new Fees(1L, "Fees1", 10);
        when(feesRepository.findById(1L)).thenReturn(Optional.of(mockFees));

        FeesResponseDTO result = feesService.getFeesById(1L);

        assertEquals(mockFees.getName(), result.getName());
        assertEquals(mockFees.getPercentage(), result.getPercentage());
    }

    @Test
    public void testGetFeesById_WhenFeesFound() {
        Fees mockFees = new Fees(1L, "Fees1", 10);
        when(feesRepository.findById(1L)).thenReturn(Optional.of(mockFees));

        FeesResponseDTO result = feesService.getFeesById(1L);

        assertEquals(mockFees.getName(), result.getName());
        assertEquals(mockFees.getPercentage(), result.getPercentage());
    }

    @Test
    public void testGetFeesById_WhenFeesNotFound() {
        when(feesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> feesService.getFeesById(1L));
    }

    @Test
    public void testCreateFees() {
        FeesRequestDTO mockFeesRequest = new FeesRequestDTO("Fees3", 30);
        Fees mockFees = new Fees(3L, "Fees3", 30);
        when(feesRepository.save(any())).thenReturn(mockFees);

        FeesResponseDTO result = feesService.createFees(mockFeesRequest);

        assertEquals(mockFees.getName(), result.getName());
        assertEquals(mockFees.getPercentage(), result.getPercentage());
    }



    @Test
    public void testUpdateFees() {
        FeesRequestDTO mockFeesRequest = new FeesRequestDTO("Fees3", 30);
        Fees mockFees = new Fees(3L, "Fees3", 30);
        when(feesRepository.findById(3L)).thenReturn(Optional.of(mockFees));
        when(feesRepository.save(any())).thenReturn(mockFees);

        FeesResponseDTO result = feesService.updateFees(3L, mockFeesRequest);

        assertEquals(mockFees.getName(), result.getName());
        assertEquals(mockFees.getPercentage(), result.getPercentage());
    }

    @Test
    public void testUpdateFees_WhenFeesNotFound() {
        FeesRequestDTO mockFeesRequest = new FeesRequestDTO("Fees3", 30);
        when(feesRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> feesService.updateFees(3L, mockFeesRequest));
    }



}

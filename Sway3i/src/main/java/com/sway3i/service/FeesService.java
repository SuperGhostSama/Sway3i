package com.sway3i.service;

import com.sway3i.dto.Fees.Request.FeesRequestDTO;
import com.sway3i.dto.Fees.Response.FeesResponseDTO;
import com.sway3i.entities.Fees;

import java.util.List;

public interface FeesService {
    List<FeesResponseDTO> getAllFees();
    FeesResponseDTO getFeesById(Long id);
    FeesResponseDTO createFees(FeesRequestDTO feesRequest);
    FeesResponseDTO updateFees(Long id, FeesRequestDTO updatedFeesRequest);
    void deleteFees(Long id);
}

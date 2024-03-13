package com.sway3i.controllers;

import com.sway3i.dto.Fees.Request.FeesRequestDTO;
import com.sway3i.dto.Fees.Response.FeesResponseDTO;
import com.sway3i.service.FeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fees")
@RequiredArgsConstructor
public class FeesController {

    private final FeesService feesService;

    @GetMapping
    public List<FeesResponseDTO> getAllFees() {
        return feesService.getAllFees();
    }

    @GetMapping("/{id}")
    public FeesResponseDTO getFeesById(@PathVariable Long id) {
        return feesService.getFeesById(id);
    }

    @PostMapping
    public FeesResponseDTO createFees(@RequestBody FeesRequestDTO feesRequest) {
        return feesService.createFees(feesRequest);
    }

    @PutMapping("/{id}")
    public FeesResponseDTO updateFees(@PathVariable Long id, @RequestBody FeesRequestDTO updatedFeesRequest) {
        return feesService.updateFees(id, updatedFeesRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFees(@PathVariable Long id) {
        feesService.deleteFees(id);
    }
}

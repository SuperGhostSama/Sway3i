package com.sway3i.service.Impl;
import com.sway3i.dto.Fees.Request.FeesRequestDTO;
import com.sway3i.dto.Fees.Response.FeesResponseDTO;
import com.sway3i.entities.Fees;
import com.sway3i.repository.FeesRepository;
import com.sway3i.service.FeesService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeesServiceImpl implements FeesService {

    private final FeesRepository feesRepository;


    public FeesServiceImpl(FeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    @Override
    public List<FeesResponseDTO> getAllFees() {
        List<Fees> fees = feesRepository.findAll();
        return fees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeesResponseDTO getFeesById(Long id) {
        Fees fees = feesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fees not found with id: " + id));
        return convertToDTO(fees);
    }

    @Override
    public FeesResponseDTO createFees(FeesRequestDTO feesRequest) {
        Fees fees = Fees.builder()
                .name(feesRequest.getName())
                .percentage(feesRequest.getPercentage())
                .build();
        Fees savedFees = feesRepository.save(fees);
        return convertToDTO(savedFees);
    }

    @Override
    public FeesResponseDTO updateFees(Long id, FeesRequestDTO updatedFeesRequest) {
        Fees existingFees = feesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fees not found with id: " + id));

        Fees updatedFees = Fees.builder()
                .id(id)
                .name(updatedFeesRequest.getName())
                .percentage(updatedFeesRequest.getPercentage())
                .build();

        Fees savedFees = feesRepository.save(updatedFees);
        return convertToDTO(savedFees);
    }

    @Override
    public void deleteFees(Long id) {
        feesRepository.deleteById(id);
    }

    private FeesResponseDTO convertToDTO(Fees fees) {
        return FeesResponseDTO.builder()
                .id(fees.getId())
                .name(fees.getName())
                .percentage(fees.getPercentage())
                .build();
    }
}

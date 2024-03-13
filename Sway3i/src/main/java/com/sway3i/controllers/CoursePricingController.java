package com.sway3i.controllers;

import com.sway3i.dto.CoursePricing.Request.CalculateDiscountedPriceRequestDTO;
import com.sway3i.dto.CoursePricing.Response.CalculateDiscountedPriceResponseDTO;
import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.PricingPlan;
import com.sway3i.service.CoursePricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course-pricing")
@RequiredArgsConstructor
public class CoursePricingController {

    private final CoursePricingService coursePricingService;

    @PostMapping("/calculate-discounted-prices")
    public ResponseEntity<CalculateDiscountedPriceResponseDTO> calculateDiscountedPrices(@RequestBody CalculateDiscountedPriceRequestDTO request) {
        CalculateDiscountedPriceResponseDTO response = coursePricingService.calculateDiscountedPrices(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

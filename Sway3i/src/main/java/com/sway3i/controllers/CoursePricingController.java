package com.sway3i.controllers;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.PricingPlan;
import com.sway3i.service.CoursePricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course-pricing")
@RequiredArgsConstructor
public class CoursePricingController {

    private final CoursePricingService coursePricingService;

    @GetMapping("/calculate")
    public ResponseEntity<Long> calculateDiscountedPrice(
            @RequestParam CourseType courseType,
            @RequestParam PricingPlan pricingPlan) {
        long discountedPrice = coursePricingService.calculateDiscountedPrice(courseType, pricingPlan);
        return new ResponseEntity<>(discountedPrice, HttpStatus.OK);
    }
}

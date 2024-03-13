package com.sway3i.service;

import com.sway3i.dto.CoursePricing.Request.CalculateDiscountedPriceRequestDTO;
import com.sway3i.dto.CoursePricing.Response.CalculateDiscountedPriceResponseDTO;

public interface CoursePricingService {
    CalculateDiscountedPriceResponseDTO calculateDiscountedPrices(CalculateDiscountedPriceRequestDTO request) ;

}

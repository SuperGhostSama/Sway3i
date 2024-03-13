package com.sway3i.dto.CoursePricing.Response;

import com.sway3i.entities.enums.PricingPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDiscountedPriceResponseDTO {
    private Map<PricingPlan, Long> discountedPrices;

}

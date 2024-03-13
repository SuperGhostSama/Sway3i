package com.sway3i.dto.CoursePricing.Request;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.PricingPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDiscountedPriceRequestDTO {
    private CourseType courseType;
    private long coursePrice;
}

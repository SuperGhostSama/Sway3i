package com.sway3i.dto.CoursePricing.Request;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.PricingPlan;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDiscountedPriceRequestDTO {
    @NotNull(message = "CourseType cannot be null")
    private CourseType courseType;

    @Positive(message = "CoursePrice must be positive")
    private long coursePrice;
}

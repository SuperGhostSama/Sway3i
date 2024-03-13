package com.sway3i.service;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.PricingPlan;

public interface CoursePricingService {
    long calculateDiscountedPrice(CourseType courseType, PricingPlan pricingPlan);

}

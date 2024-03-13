package com.sway3i.service.Impl;

import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.PricingPlan;
import com.sway3i.repository.FeesRepository;
import com.sway3i.service.CoursePricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoursePricingServiceImpl implements CoursePricingService {

    private final FeesRepository feesRepository;

    @Override
    public long calculateDiscountedPrice(CourseType courseType, PricingPlan pricingPlan) {

        return 0;
    }
}

package com.sway3i.service.Impl;

import com.sway3i.dto.CoursePricing.Request.CalculateDiscountedPriceRequestDTO;
import com.sway3i.dto.CoursePricing.Response.CalculateDiscountedPriceResponseDTO;
import com.sway3i.entities.Fees;
import com.sway3i.entities.enums.CourseType;
import com.sway3i.entities.enums.PricingPlan;
import com.sway3i.repository.FeesRepository;
import com.sway3i.service.CoursePricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursePricingServiceImpl implements CoursePricingService {

    private final FeesRepository feesRepository;

    @Override
    public CalculateDiscountedPriceResponseDTO calculateDiscountedPrices(CalculateDiscountedPriceRequestDTO request) {
        Map<PricingPlan, Long> discountedPrices = new HashMap<>();

        long priceWithFees = calculateBasePriceWithFees(request.getCoursePrice());

        if (request.getCourseType() == CourseType.Online) {
            discountedPrices.put(PricingPlan.ONE_MONTH, calculatePriceForOneMonthOnline(priceWithFees));
        } else {
            discountedPrices.put(PricingPlan.ONE_MONTH, priceWithFees);
        }

        discountedPrices.put(PricingPlan.SIX_MONTHS, calculateDiscountedPriceForSixMonths(request.getCourseType(), priceWithFees));
        discountedPrices.put(PricingPlan.TEN_MONTHS, calculateDiscountedPriceForTenMonths(request.getCourseType(), priceWithFees));

        return new CalculateDiscountedPriceResponseDTO(discountedPrices);
    }

    private long calculateDiscountedPriceForSixMonths(CourseType courseType, long coursePriceWithFees) {
        switch (courseType) {
            case Online:
                return calculatePriceForSixMonthsOnline(coursePriceWithFees);
            case In_Person:
                return calculatePriceForSixMonthsInPerson(coursePriceWithFees);
            default:
                throw new IllegalArgumentException("Invalid course type: " + courseType);
        }
    }

    private long calculateDiscountedPriceForTenMonths(CourseType courseType, long coursePriceWithFees) {
        switch (courseType) {
            case Online:
                return calculatePriceForTenMonthsOnline(coursePriceWithFees);
            case In_Person:
                return calculatePriceForTenMonthsInPerson(coursePriceWithFees);
            default:
                throw new IllegalArgumentException("Invalid course type: " + courseType);
        }
    }


    //Helper methods responsible for calculations

    public long calculateBasePriceWithFees(long coursePrice) {

        Optional<Fees> feesOptional = feesRepository.findById(1L);

        if (feesOptional.isPresent()) {
            Fees fees = feesOptional.get();
            int basePercentage = fees.getPercentage();

            long priceWithFees = coursePrice + (coursePrice * basePercentage / 100);
            return priceWithFees;
        } else {
            throw new RuntimeException("Fee not found with ID: 1");
        }

    }

    public long calculatePriceForOneMonthOnline(long coursePriceWithfees) {
        Optional<Fees> oneMonthOnlineReductionOptional = feesRepository.findById(6L);

        if (oneMonthOnlineReductionOptional.isPresent()) {
            Fees oneMonthOnlineReduction = oneMonthOnlineReductionOptional.get();
            int basePercentage = oneMonthOnlineReduction.getPercentage();

            long priceForOneMonthOnline = coursePriceWithfees - (coursePriceWithfees * basePercentage / 100);
            return priceForOneMonthOnline;
        } else {
            throw new RuntimeException("Fee not found with ID: 6");
        }

    }

    public long calculatePriceForSixMonthsOnline(long coursePriceWithfees) {
        Optional<Fees> sixMonthOnlineReductionOptional = feesRepository.findById(2L);

        if (sixMonthOnlineReductionOptional.isPresent()) {
            Fees sixMonthOnlineReduction = sixMonthOnlineReductionOptional.get();
            int basePercentage = sixMonthOnlineReduction.getPercentage();

            // Calculate the discounted price for six months online
            long discountedPrice = (coursePriceWithfees - (coursePriceWithfees * basePercentage / 100)) * 6;

            return discountedPrice;
        } else {
            throw new RuntimeException("Fee not found with ID: 2");
        }
    }


    public long calculatePriceForTenMonthsOnline(long coursePriceWithfees) {
        Optional<Fees> tenMonthOnlineReductionOptional = feesRepository.findById(3L);

        if (tenMonthOnlineReductionOptional.isPresent()) {
            Fees tenMonthOnlineReduction = tenMonthOnlineReductionOptional.get();
            int basePercentage = tenMonthOnlineReduction.getPercentage();

            long priceForTenMonthsOnline = (coursePriceWithfees - (coursePriceWithfees * basePercentage / 100)) * 10;
            return priceForTenMonthsOnline;
        } else {
            throw new RuntimeException("Fee not found with ID: 3");
        }
    }

    public long calculatePriceForSixMonthsInPerson(long coursePriceWithfees) {
        Optional<Fees> sixMonthInPersonReductionOptional = feesRepository.findById(4L);

        if (sixMonthInPersonReductionOptional.isPresent()) {
            Fees sixMonthInPersonReduction = sixMonthInPersonReductionOptional.get();
            int basePercentage = sixMonthInPersonReduction.getPercentage();

            long priceForSixMonthsInPerson = (coursePriceWithfees - (coursePriceWithfees * basePercentage / 100)) * 6;
            return priceForSixMonthsInPerson;
        } else {
            throw new RuntimeException("Fee not found with ID: 4");
        }
    }

    public long calculatePriceForTenMonthsInPerson(long coursePriceWithfees) {
        Optional<Fees> tenMonthInPersonReductionOptional = feesRepository.findById(5L);

        if (tenMonthInPersonReductionOptional.isPresent()) {
            Fees tenMonthInPersonReduction = tenMonthInPersonReductionOptional.get();
            int basePercentage = tenMonthInPersonReduction.getPercentage();

            long priceForTenMonthsInPerson = (coursePriceWithfees - (coursePriceWithfees * basePercentage / 100)) * 10;
            return priceForTenMonthsInPerson;
        } else {
            throw new RuntimeException("Fee not found with ID: 5");
        }
    }


}

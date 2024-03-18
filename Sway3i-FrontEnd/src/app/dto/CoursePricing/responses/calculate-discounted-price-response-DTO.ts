import { PricingPlan } from '../../../modal/enums/pricing-plan-enum';

export interface CalculateDiscountedPriceResponseDTO {
    discountedPrices: Map<PricingPlan, number>;
}

import { PricingPlan } from "src/app/modal/enums/pricing-plan-enum";

export interface StudentsInCourseRequestDTO {
    studentId: number;
    courseId: number;
    pricingPlan: PricingPlan; 
  }
  
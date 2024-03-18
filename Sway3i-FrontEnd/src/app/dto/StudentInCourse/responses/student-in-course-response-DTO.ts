import { PricingPlan } from "src/app/modal/enums/pricing-plan-enum";

export interface StudentsInCourseResponseDTO {
  id: number;
  createdAt: string; 
  isExpired: boolean;
  studentId: number;
  courseId: number;
  pricingPlan:PricingPlan;
}

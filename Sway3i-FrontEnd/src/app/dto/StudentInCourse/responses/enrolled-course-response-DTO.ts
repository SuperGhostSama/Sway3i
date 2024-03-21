import { PricingPlan } from "src/app/modal/enums/pricing-plan-enum";

export interface EnrolledCourseResponseDTO {
    id: number;
    createdAt: string; 
    isExpired: boolean;
    studentId: number;
    courseId: number;
    courseName: string;
    courseDetails: string;
    courseSubject: string;
    createdByFirstName: string;
    createdByLastName: string;
    createdByCity: string;
    pricingPlan: PricingPlan; 
    link: string;
    address: string;
  }
  
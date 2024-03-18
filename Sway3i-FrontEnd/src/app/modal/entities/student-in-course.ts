import { User } from './user'; 
import { Course } from './course'; 
import { PricingPlan } from '../enums/pricing-plan-enum';

export interface StudentsInCourse {
  id: number;
  createdAt: Date;
  isExpired: boolean;
  student: User;
  course: Course;
  pricingPlan: PricingPlan;
}

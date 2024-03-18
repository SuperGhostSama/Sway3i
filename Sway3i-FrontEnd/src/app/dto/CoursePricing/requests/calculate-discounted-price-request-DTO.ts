import { CourseType } from "src/app/modal/enums/course-type-enum";

export interface CalculateDiscountedPriceRequestDTO {
  courseType: CourseType;
  coursePrice: number;
}

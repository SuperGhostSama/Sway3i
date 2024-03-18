import { CourseType } from "src/app/modal/enums/course-type-enum";
import { EducationLevel } from "src/app/modal/enums/education-level-enum";

export interface CourseRequestDTO {
  createdByUserId: number;
  subject: string;
  courseDetails: string;
  courseIsFor: string;
  price: number;
  city: string;
  educationLevel: EducationLevel;
  type: CourseType;
  maxStudents: number;
  programIds: number[];
}

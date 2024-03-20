import { User } from './user'; 
import { Program } from './program'; 
import { Fees } from './fees'; 
import { EducationLevel } from '../enums/education-level-enum';
import { CourseType } from '../enums/course-type-enum';

export interface Course {
  id: number;
  createdAt: Date;
  createdBy: User;
  subject: string;
  courseName: string;
  courseDetails: string;
  courseIsFor: string;
  price: number;
  city: string;
  educationLevel: EducationLevel;
  type: CourseType;
  maxStudents: number;
  programs: Program[];
  fees: Fees[];
  link: string;
  address: string;
}
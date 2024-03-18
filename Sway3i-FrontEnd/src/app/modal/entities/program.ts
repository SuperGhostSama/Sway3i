import { Days } from '../enums/days-enum';
import { Course } from './course'; 

export interface Program {
  id: number;
  day: Days;
  time: string;
  courses: Course[];
}
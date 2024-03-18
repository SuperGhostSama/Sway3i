import { DemandStatus } from '../enums/demand-status-enum';
import { EducationLevel } from '../enums/education-level-enum';
import { User } from './user';

export interface TeacherDemand {
  id: number;
  createdBy: User;
  subject: string;
  educationLevel: EducationLevel;
  description: string;
  status: DemandStatus;
}

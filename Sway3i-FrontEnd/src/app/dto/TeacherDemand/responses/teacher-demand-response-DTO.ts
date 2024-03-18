import { User } from "src/app/modal/entities/user";
import { DemandStatus } from "src/app/modal/enums/demand-status-enum";
import { EducationLevel } from "src/app/modal/enums/education-level-enum";

export interface TeacherDemandResponseDTO {
  id: number;
  createdBy: User;
  subject: string;
  educationLevel: EducationLevel;
  description: string;
  status: DemandStatus;
}

import { DemandStatus } from "src/app/modal/enums/demand-status-enum";
import { EducationLevel } from "src/app/modal/enums/education-level-enum";

export interface TeacherDemandRequestDTO {
    createdById: number;
    subject: string;
    educationLevel: EducationLevel;
    description: string;
    status: DemandStatus;

}
  
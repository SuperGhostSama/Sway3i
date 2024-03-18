import { Days } from "src/app/modal/enums/days-enum";

export interface ProgramResponseDTO {
  id: number;
  day: Days;
  time: string; 
}

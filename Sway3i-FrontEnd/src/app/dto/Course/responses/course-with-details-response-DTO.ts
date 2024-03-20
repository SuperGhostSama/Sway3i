import { ProgramDTO } from "../../Utils/program-DTO";
import { UserDTO } from "../../Utils/user-DTO";

export interface CourseWithDetailsResponseDTO {
  id: number;
  createdAt: string; // Date
  createdByUser: UserDTO;
  subject: string;
  courseName: string;
  courseDetails: string;
  courseIsFor: string;
  price: number;
  city: string;
  educationLevel: string;
  type: string;
  maxStudents: number;
  programs: ProgramDTO[];
  link: string;
  address: string;
}

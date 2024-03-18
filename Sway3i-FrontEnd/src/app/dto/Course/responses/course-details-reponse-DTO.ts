import { Fees } from "src/app/modal/entities/fees";

export interface CourseDetailsResponseDTO {
  price: number;
  fees: Fees[];
}

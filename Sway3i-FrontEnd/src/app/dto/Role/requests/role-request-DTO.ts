import { Authority } from "src/app/modal/entities/authority";

export interface RoleRequestDTO {
  name: string;
  authorities: Authority[];
  isDefault: boolean;
}

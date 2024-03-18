import { Role } from "src/app/modal/entities/role";


export interface AuthenticationResponse {
  token: string;
  refreshToken: string;
  firstName: string;
  lastName: string;
  city: string;
  email: string;
  role: Role;
}

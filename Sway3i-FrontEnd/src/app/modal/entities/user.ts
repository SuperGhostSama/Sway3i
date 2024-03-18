import { Role } from './role';

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  city: string;
  email: string;
  password: string;
  role: Role;
  refreshToken: string;
  isValid: boolean;
}

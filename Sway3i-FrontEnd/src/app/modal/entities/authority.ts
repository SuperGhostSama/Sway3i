import { Role } from './role';

export interface Authority {
  id: number;
  roles: Role[];
  name: string;
}

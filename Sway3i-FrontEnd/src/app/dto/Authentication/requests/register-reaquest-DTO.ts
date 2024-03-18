export interface RegisterRequest {
    firstName: string;
    lastName: string;
    city: string;
    role: string;
    email: string;
    password: string;
    isValid?: boolean;
}
  
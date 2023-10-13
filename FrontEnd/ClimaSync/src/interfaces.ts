export interface User {
  first_name: string;
  last_name: string;
  email: string;
  password: string;
}

export interface ExistingUser {
  email: string;
  password: string;
}

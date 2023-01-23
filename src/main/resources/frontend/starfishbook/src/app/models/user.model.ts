import { Roles } from "./roles.model";

export class User {
    userid?: number;
    email: String;
    password: String;
    roles?: Roles;
  }
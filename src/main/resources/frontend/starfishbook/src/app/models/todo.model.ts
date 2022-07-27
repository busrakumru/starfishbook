import { Todolist } from "./todolist.model";

export class Todo {
    id?: number;
    title?:string='';
    createdAt?: Date;
    todolist: Todolist[];
    appointmentTime?:Date;
  }
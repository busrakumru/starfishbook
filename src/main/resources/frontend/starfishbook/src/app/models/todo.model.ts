import { Todolist } from "./todolist.model";

export class Todo {
    id?: number;
    title?:string='';
    createdAt?: Date;
<<<<<<< HEAD
    todolist?: Todolist;
=======
    todolist: Todolist[];
>>>>>>> todo
    appointmentTime?:Date;
  }
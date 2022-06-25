import { Todolist } from "./todolist.model";

export class Todo {
    id: number;
    title:string='';
    createt: Date;
    todoList: Array<Todolist>;
  }
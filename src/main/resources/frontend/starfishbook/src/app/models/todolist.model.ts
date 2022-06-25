import { Todo } from "./todo.model";

export class Todolist {
    id: number;
    text:string='';
    finished: boolean = false;
    todos: Todo;
  }
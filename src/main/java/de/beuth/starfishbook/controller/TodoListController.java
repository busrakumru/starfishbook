package de.beuth.starfishbook.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.beuth.starfishbook.model.TodoList;
//import de.beuth.starfishbook.service.ToDosService;
import de.beuth.starfishbook.service.TodoListService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/users/")
public class TodoListController {

    @Autowired
    private final TodoListService todoListService;
    // private final ToDosService todoService;

    public TodoListController(TodoListService todoListService) {
        // this.todoService = todoService;
        this.todoListService = todoListService;
    }

    @GetMapping("todolist")
    public List<TodoList> getTodoList() {
        return this.todoListService.getTodoList();
    }

    @GetMapping("todolist/{id}")
    public TodoList getTodoListById(@PathVariable(value = "id") Long todolistId) {
        return this.todoListService.findTodoListById(todolistId);
    }

    @GetMapping("todo/{todoId}/todolist")
    public ResponseEntity<List<TodoList>> getTodolistsByTodosId(@PathVariable(value = "todoId") Long todoId) {
        List<TodoList> todolist = todoListService.findByTodosId(todoId);
        return new ResponseEntity<>(todolist, HttpStatus.OK);
    }

    @PostMapping("todolist")
    public TodoList addTodoList(@RequestBody TodoList todosList) {
        return this.todoListService.addTodoList(todosList);
    }

    /*
     * @PostMapping("todo/{todoId}/todolist")
     * public TodoList createTodolistByTodosId(@PathVariable(value = "todoId") Long
     * todoId,
     * 
     * @RequestBody TodoList request) {
     * 
     * ToDos todos = this.todoService.findTodoById(todoId);
     * request.setTodos(todos);
     * return this.todoListService.save(request);
     * }
     */

    @PutMapping("todolist/{id}")
    public TodoList updateTodolistbyTodosId(@PathVariable(value = "id") Long todolistId,
            @RequestBody TodoList todoslist) {

        TodoList todolist = this.todoListService.findTodoListById(todolistId);
        todolist.setText(todoslist.getText());
        todolist.setFinished(todoslist.isFinished());
        todolist.setTodos(todoslist.getTodos());
        TodoList updatedTodoList = this.todoListService.save(todolist);
        return updatedTodoList;
    }

    /*
     * @PutMapping("todo/{todoId}/todolist/{id}")
     * public TodoList updateTodoList(@PathVariable(value = "id") Long todolistId,
     * 
     * @PathVariable(value = "todoId") Long todoId, @RequestBody TodoList todoList)
     * throws NoteNotFoundException {
     * TodoList todolist = todoListService.findByIdAndTodosId(todolistId, todoId);
     * todolist.setText(todoList.getText());
     * todolist.setFinished(todoList.isFinished());
     * todolist.setTodos(todoList.getTodos());
     * TodoList updatedTodoList = this.todoListService.save(todolist);
     * return updatedTodoList;
     * }
     */

    @DeleteMapping("todolist/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.todoListService.delete(id);
    }

    /*
     * @DeleteMapping("todo/{todoId}/todolist/{id}")
     * public ResponseEntity<List<TodoList>>
     * deleteTodolistbyTodosId(@PathVariable(value = "todoId") Long todoId,
     * 
     * @PathVariable(value = "id") Long todolistId) {
     * this.todoListService.deleteTodo(todolistId, todoId);
     * return ResponseEntity.ok().build();
     * }
     */
}

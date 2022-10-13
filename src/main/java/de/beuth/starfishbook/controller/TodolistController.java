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
import de.beuth.starfishbook.model.Todolist;
//import de.beuth.starfishbook.service.TodosService;
import de.beuth.starfishbook.service.TodolistService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/users/")
public class TodolistController {

    @Autowired
    private final TodolistService todoListService;
    // private final TodosService todoService;

    public TodolistController(TodolistService todoListService) {
        // this.todoService = todoService;
        this.todoListService = todoListService;
    }

    @GetMapping("todolist")
    public List<Todolist> getTodolist() {
        return this.todoListService.getTodolist();
    }

    @GetMapping("todolist/{id}")
    public Todolist getTodolistById(@PathVariable(value = "id") Long todolistId) {
        return this.todoListService.findTodolistById(todolistId);
    }

    @GetMapping("todo/{todoId}/todolist")
    public ResponseEntity<List<Todolist>> getTodolistsByTodosId(@PathVariable(value = "todoId") Long todoId) {
        List<Todolist> todolist = todoListService.findByTodosId(todoId);
        return new ResponseEntity<>(todolist, HttpStatus.OK);
    }

    @PostMapping("todolist")
    public Todolist addTodolist(@RequestBody Todolist todoslist) {
        return this.todoListService.addTodolist(todoslist);
    }

    /*
     * @PostMapping("todo/{todoId}/todolist")
     * public Todolist createTodolistByTodosId(@PathVariable(value = "todoId") Long
     * todoId,
     * 
     * @RequestBody Todolist request) {
     * 
     * Todos todos = this.todoService.findTodoById(todoId);
     * request.setTodos(todos);
     * return this.todoListService.save(request);
     * }
     */

    @PutMapping("todolist/{id}")
    public Todolist updateTodolistbyTodosId(@PathVariable(value = "id") Long todolistId,
            @RequestBody Todolist todoslist) {
                return this.todoListService.updateTodo(todolistId,todoslist);
    }

    /*
     * @PutMapping("todo/{todoId}/todolist/{id}")
     * public Todolist updateTodolist(@PathVariable(value = "id") Long todolistId,
     * 
     * @PathVariable(value = "todoId") Long todoId, @RequestBody Todolist todoList)
     * throws NoteNotFoundException {
     * Todolist todolist = todoListService.findByIdAndTodosId(todolistId, todoId);
     * todolist.setText(todoList.getText());
     * todolist.setFinished(todoList.isFinished());
     * todolist.setTodos(todoList.getTodos());
     * Todolist updatedTodolist = this.todoListService.save(todolist);
     * return updatedTodolist;
     * }
     */

    @DeleteMapping("todolist/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.todoListService.delete(id);
    }

    /*
     * @DeleteMapping("todo/{todoId}/todolist/{id}")
     * public ResponseEntity<List<Todolist>>
     * deleteTodolistbyTodosId(@PathVariable(value = "todoId") Long todoId,
     * 
     * @PathVariable(value = "id") Long todolistId) {
     * this.todoListService.deleteTodo(todolistId, todoId);
     * return ResponseEntity.ok().build();
     * }
     */
}

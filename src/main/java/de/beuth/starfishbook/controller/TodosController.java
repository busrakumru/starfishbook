package de.beuth.starfishbook.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.beuth.starfishbook.exception.NoteNotFoundException;
import de.beuth.starfishbook.model.Todos;
import de.beuth.starfishbook.repository.TodosRepository;
import de.beuth.starfishbook.service.TodosService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/users")

public class TodosController {

    @Autowired
    private final TodosService todoService;

    public TodosController(TodosService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("todo")
    public List<Todos> getTodos() {
        return this.todoService.getTodos();
    }

    @PostMapping("todo")
    public Todos addTodos(@RequestBody Todos toDos) {
        return this.todoService.addTodo(toDos);
    }

    /*
     * @PutMapping("todo/{id}")
     * public Todos update(@PathVariable(value = "id") Long todoId, @RequestBody
     * Todos todosDetail)
     * throws NoteNotFoundException {
     * Todos updatedTodos = this.todoService.updateTodo(todoId, todosDetail);
     * return updatedTodos;
     * }
     */

    @PatchMapping("todo/{id}")
    public Todos updateTodoWithMap(@PathVariable(value = "id") Long id, @RequestBody Map<Object, Object> objectMap) {
        return todoService.updateTodoWithMap(id, objectMap);

    }

    @DeleteMapping("todo/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.todoService.delete(id);
    }
}

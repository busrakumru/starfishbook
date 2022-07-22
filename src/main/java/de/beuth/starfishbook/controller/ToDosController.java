package de.beuth.starfishbook.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.beuth.starfishbook.exception.NoteNotFoundException;
import de.beuth.starfishbook.model.ToDos;
import de.beuth.starfishbook.service.ToDosService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/users")
public class ToDosController {

    @Autowired
    private final ToDosService todoService;

    public ToDosController(ToDosService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("todo")
    public List<ToDos> getToDos() {
        return this.todoService.getTodos();
    }

    @GetMapping("todo/{id}")
    public ToDos getToDosById(@PathVariable(value = "id") Long todoId) throws NoteNotFoundException {
        return this.todoService.findTodoById(todoId);
    }

    @PostMapping("todo")
    public ToDos addToDos(@RequestBody ToDos toDos) {
        return this.todoService.addTodo(toDos);
    }

    @PutMapping("todo/{id}")
    public ToDos updateNote(@PathVariable(value = "id") Long todoId, @RequestBody ToDos todosDetail)
            throws NoteNotFoundException {
        ToDos updatedToDos = this.todoService.updateTodo(todoId, todosDetail);
        return updatedToDos;
    }

    @DeleteMapping("todo/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.todoService.delete(id);
    }
}

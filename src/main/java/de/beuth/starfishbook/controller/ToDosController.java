package de.beuth.starfishbook.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.beuth.starfishbook.exception.NoteNotFoundException;
import de.beuth.starfishbook.model.ToDos;
import de.beuth.starfishbook.repository.ToDosRepository;
import de.beuth.starfishbook.service.ToDosService;


@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/users")
public class ToDosController {

    @Autowired
    private ToDosRepository todoRepository;
    //private final ToDosService todoService;    


    public ToDosController(ToDosRepository todoRepository,ToDosService todoService) {
        this.todoRepository = todoRepository;
          //this.todoService = todoService;
    }
 
    @GetMapping("todos")
    public List<ToDos> getToDos() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return todoRepository.findAll(sortByCreatedAtDesc);
       // return this.todoRepository.findAll();
    }

    // Get a Single Note
    @GetMapping("todos/{id}")
    public ToDos getToDosById(@PathVariable(value = "id") Long noteId) throws NoteNotFoundException {
        return this.todoRepository.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException(noteId));
    }

    @PostMapping("todos")
    public ToDos addToDos(@RequestBody ToDos toDos) {
        return this.todoRepository.save(toDos);
    }

   
    @PutMapping("todos/{id}")
    public ToDos updateNote(@PathVariable(value = "id") Long todoId, @RequestBody ToDos todosDetail)
            throws NoteNotFoundException {

        ToDos todo = this.todoRepository.findById(todoId)
                .orElseThrow(() -> new NoteNotFoundException(todoId));

        todo.setText(todosDetail.getText());
        todo.setFinish(todosDetail.isFinished());
        todo.setTitle(todosDetail.getTitle());
        todo.setCreatedAt(todosDetail.getCreatedAt());
 
        ToDos updatedToDos = this.todoRepository.save(todo);

        return updatedToDos;
    }

    
    @DeleteMapping("todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long todoId) throws NoteNotFoundException {

        ToDos todo = this.todoRepository.findById(todoId)
                .orElseThrow(() -> new NoteNotFoundException(todoId));

        this.todoRepository.delete(todo);

        return ResponseEntity.ok().build();
    }

}

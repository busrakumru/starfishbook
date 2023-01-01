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

    private TodosRepository todosRepository;

    public TodosController(TodosService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("todo")
    public List<Todos> getTodos() {
        return this.todoService.getTodos();
    }

    @GetMapping("todo/{id}")
    public Todos getTodosById(@PathVariable(value = "id") Long todoId) throws NoteNotFoundException {
        return this.todoService.findTodoById(todoId);
    }

    @PostMapping("todo")
    public Todos addTodos(@RequestBody Todos toDos) {
        return this.todoService.addTodo(toDos);
    }

    /*
     * @PostMapping("todo")
     * public ResponseEntity<Todos>create(@RequestBody Todos todos){
     * Todos savetodo = this.todoService.save(todos);
     * 
     * URI location =
     * ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(
     * savetodo.getId()).toUri();
     * return ResponseEntity.created(location).body(savetodo);
     * 
     * }
     */
    /*@PatchMapping("/todo/{id}")
    public ResponseEntity<?> partialUpdateName(
             @PathVariable(value = "id") Long id,@RequestBody Todos partialUpdate) {

        todoService.updateTodo(id,partialUpdate);
        return ResponseEntity.ok("resource address updated");
    }*/

    @PutMapping("todo/{id}")
    public Todos update(@PathVariable(value = "id") Long todoId, @RequestBody Todos todosDetail)
            throws NoteNotFoundException {
        Todos updatedTodos = this.todoService.updateTodo(todoId, todosDetail);
        return updatedTodos;
    }

      @PatchMapping("todo/{id}")
      public Todos updateTodoWithMap(@PathVariable(value="id") Long id, @RequestBody
      Map<Object,Object> objectMap ){    
     return todoService.updateTodoWithMap(id, objectMap); 
      
      }
     
    /* 
     * private Todos mapPersistenceModelToRestModel(Todos ticket){
     * Todos ticketRestModel = new Todos();
     * ticketRestModel.setTitle(ticket.getTitle());
     * ticketRestModel.setAppointmentTime(ticket.getAppointmentTime());
     * return ticketRestModel;
     * }
     * 
     * 
     * private void mapRestModelToPersistenceModel(Todos ticketRestModel, Todos
     * ticket){
     * ticket.setTitle(ticketRestModel.getTitle());
     * ticket.setAppointmentTime(ticketRestModel.getAppointmentTime());
     * 
     * }
     * 
     * @PatchMapping("todo/{id}")
     * public ResponseEntity patchTicket(@PathVariable(value = "id") long
     * ticketId, @RequestBody Map<String, Object> changes){
     * 
     * //Fetch the data from the database
     * Todos ticket = todosRepository.findById(ticketId).get();
     * 
     * //Map the persistent data to the REST dto
     * //This is done to enforce REST validation groups
     * Todos ticketRestModel = mapPersistenceModelToRestModel(ticket);
     * 
     * //apply the changes to the REST model.
     * changes.forEach(
     * (change, value) -> {
     * switch (change){
     * case "title": ticketRestModel.setTitle((String) value); break;
     * case "appointmentTime": ticketRestModel.setAppointmentTime((Date) value);
     * break;
     * }
     * }
     * );
     * 
     * 
     * mapRestModelToPersistenceModel(ticketRestModel, ticket);
     * todosRepository.save(ticket);
     * return ResponseEntity.ok().build();
     * }
     */

    @DeleteMapping("todo/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.todoService.delete(id);
    }
}

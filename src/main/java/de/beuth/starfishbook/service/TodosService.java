package de.beuth.starfishbook.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.beuth.starfishbook.model.Todos;
import de.beuth.starfishbook.repository.TodosRepository;

@Service
public class TodosService {

    private final TodosRepository todoRepository;

    @Autowired
    public TodosService(TodosRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todos> getTodos() {
        List<Todos> allTodos = new ArrayList<>();
        todoRepository.findAll().forEach(allTodos::add);
        return allTodos;
    }

    public Todos findTodoById(Long id) {
        return this.todoRepository.findTodoById(id);
    }

    public Todos addTodo(Todos request) {
        Todos todo = new Todos();
        todo.setTitle(request.getTitle());
        todo.setCreatedAt(request.getCreatedAt());
        todo.setTodolist(request.getTodolist());
        todo.setAppointmentTime(request.getAppointmentTime());
        return this.todoRepository.save(todo);
    }

    public Todos save(Todos request) {
        return this.todoRepository.save(request);
    }

    public Todos updateTodo(Long id, Todos request) {
        Todos forUpdate = this.todoRepository.findTodoById(id);
        forUpdate.setTitle(request.getTitle());
        forUpdate.setCreatedAt(request.getCreatedAt());
        forUpdate.setAppointmentTime(request.getAppointmentTime());
        return this.todoRepository.save(forUpdate);
    }

    public Boolean delete(Long id) {
        this.todoRepository.deleteById(id);
        return this.todoRepository.existsById(id);
    }
}

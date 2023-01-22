package de.beuth.starfishbook.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import de.beuth.starfishbook.model.Todos;
import de.beuth.starfishbook.repository.TodosRepository;

@Service
public class TodosService {

    private final TodosRepository todoRepository;

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

    public Boolean delete(Long id) {
        this.todoRepository.deleteById(id);
        return this.todoRepository.existsById(id);
    }

    public Todos updateTodoWithMap(Long id, Map<Object, Object> objectMap) {

        Optional<Todos> todos = todoRepository.findById(id);

        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Todos.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, todos.get(), value);
        });

        return todoRepository.save(todos.get());// todoService.updateTodo(todos);
    }

}

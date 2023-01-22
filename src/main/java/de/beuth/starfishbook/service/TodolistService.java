package de.beuth.starfishbook.service;

import java.lang.reflect.Field;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import de.beuth.starfishbook.model.Todolist;
import de.beuth.starfishbook.repository.TodolistRepository;
import de.beuth.starfishbook.repository.TodosRepository;

@Service
public class TodolistService {

    @Autowired
    public final TodolistRepository todoListRepository;

    @Autowired
    public final TodosRepository todosRepository;

    public TodolistService(TodolistRepository todoListRepository, TodosRepository todosRepository) {
        this.todoListRepository = todoListRepository;
        this.todosRepository = todosRepository;

    }

    public List<Todolist> getTodolist() {
        List<Todolist> allTodos = new ArrayList<>();
        todoListRepository.findAll().forEach(allTodos::add);
        return allTodos;
    }

    public Todolist findTodolistById(Long id) {
        return todoListRepository.findTodolistById(id);
    }

    public List<Todolist> findByTodosId(Long todoId) {
        return todoListRepository.findByTodosId(todoId);
    }

    public Todolist save(Todolist request) {
        return this.todoListRepository.save(request);
    }

    public Todolist addTodolist(Todolist request) {

        Todolist todo = new Todolist();
        todo.setText(request.getText());
        todo.setFinished(request.isFinished());
        todo.setTodos(request.getTodos());
        return this.todoListRepository.save(request);
    }

    /*
     * public Todolist updateTodo(Long id, Todolist request) {
     * Todolist update = todoListRepository.findTodolistById(id);
     * update.setText(request.getText());
     * update.setFinished(request.isFinished());
     * update.setTodos(request.getTodos());
     * return this.todoListRepository.save(update);
     * }
     */
    public Todolist updateWithMap(Long id, Map<Object, Object> objectMap) {

        Optional<Todolist> todolist = todoListRepository.findById(id);

        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(Todolist.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, todolist.get(), value);
        });

        return todoListRepository.save(todolist.get());
    }

    public Boolean delete(Long id) {
        this.todoListRepository.deleteById(id);
        return this.todoListRepository.existsById(id);
    }

}

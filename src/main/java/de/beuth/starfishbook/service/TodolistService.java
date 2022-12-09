package de.beuth.starfishbook.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.beuth.starfishbook.model.Todolist;
import de.beuth.starfishbook.repository.TodolistRepository;

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

    /*
     * public Todolist addTodolist(Todolist request) {
     * Todolist todo = new Todolist();
     * todo.setText(request.getText());
     * todo.setFinished(request.isFinished());
     * todo.setTodos(request.getTodos());
     * return this.todoListRepository.save(todo);
     * }
     */

    public Todolist addTodolist(Todolist request) {
        /*
         * Todos todos=
         * todosRepository.findById(request.getTodos().getId()).orElse(null);
         * if (null == todos) {
         * todos = new Todos();
         * }
         */

        // todos.setId(request.getTodos().getId());
        // request.setTodos(todos);
        Todolist todo = new Todolist();
        todo.setText(request.getText());
        todo.setFinished(request.isFinished());
        todo.setTodos(request.getTodos());
        return this.todoListRepository.save(request);
    }

    public Todolist updateTodo(Long id, Todolist request) {
        Todolist update = todoListRepository.findTodolistById(id);
        update.setText(request.getText());
        update.setFinished(request.isFinished());
        update.setTodos(request.getTodos());
        return this.todoListRepository.save(update);
    }

    public Boolean delete(Long id) {
        this.todoListRepository.deleteById(id);
        return this.todoListRepository.existsById(id);
    }

}

package de.beuth.starfishbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.beuth.starfishbook.model.ToDos;
import de.beuth.starfishbook.repository.ToDosRepository;
//import de.beuth.starfishbook.request.ToDosRequest;
import de.beuth.starfishbook.request.ToDosRequest;

@Service
public class ToDosService {
    
	private final ToDosRepository todoRepository;

    @Autowired
    public ToDosService(ToDosRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<ToDos> getTodos() {
        List<ToDos> allTodos = new ArrayList<>();

        todoRepository.findAll().forEach(allTodos::add);

        return allTodos;
    }

    public  ToDos findById(Long id){
          return this.todoRepository.findTodoById(id);
    }

    public Long addTodo ( ToDos request){
      ToDos todo = new ToDos();
        todo.setTitle(request.getTitle());
        todo.setCreatedAt(request.getCreatedAt());
        todo.setTodoList(request.getTodoList());
        return this.todoRepository.save(todo).getId();
    }

    public ToDos updateTodo(Long id, ToDos request){
       ToDos forUpdate =  this.todoRepository.findTodoById(id);
            forUpdate.setTitle(request.getTitle());
            forUpdate.setCreatedAt(request.getCreatedAt());
            return this.todoRepository.save(forUpdate);
    }

    public void delete(Long id) {
        ToDos post = findById(id);
        this.todoRepository.delete(post);
       
    }
}
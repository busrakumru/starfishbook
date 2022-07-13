package de.beuth.starfishbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.beuth.starfishbook.model.ToDos;
import de.beuth.starfishbook.model.TodoList;
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

    public  ToDos findTodoById(Long id){
          return this.todoRepository.findTodoById(id);
    }
    
    public ToDos addTodo ( ToDos request){  
      ToDos todo = new ToDos();
        todo.setTitle(request.getTitle());
        todo.setCreatedAt(request.getCreatedAt());
        todo.setTodoList(request.getTodoList());
        return this.todoRepository.save(todo);
    }
    public ToDos save(ToDos request) {
        return this.todoRepository.save(request);
    }

    public ToDos updateTodo(Long id, ToDos request){
       ToDos forUpdate =  this.todoRepository.findTodoById(id);
            forUpdate.setTitle(request.getTitle());
            forUpdate.setCreatedAt(request.getCreatedAt());
            return this.todoRepository.save(forUpdate);
    }

    /*public void delete(Long id) {
        ToDos post = findTodoById(id);
        this.todoRepository.delete(post);
       
    }*/
    public Boolean delete(Long id) {
        this.todoRepository.deleteById(id);
        return this.todoRepository.existsById(id);
    }
}
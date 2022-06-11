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

    public  Optional<ToDos> findById(Long id){
          return todoRepository.findById(id);
    }

    public Long addTodo ( ToDosRequest request){
      ToDos todo = new ToDos();
        todo.setText(request.getText());
        todo.setFinish(request.isFinished());
        todo.setTitle(request.getTitle());
        todo.setCreatedAt(request.getCreatedAt());

        return todoRepository.save(todo).getId();
    }

    public void updateTodo(Long id, ToDosRequest request){
       Optional<ToDos> todo = findById(id);
        if (todo.isPresent()) {
            ToDos forUpdate = todo.get();
            forUpdate.setText(request.getText());
            forUpdate.setFinish(request.isFinished());
            forUpdate.setTitle(request.getTitle());
            forUpdate.setCreatedAt(request.getCreatedAt());
            todoRepository.save(forUpdate);
        }
    }

    public void delete(Long id) {
        Optional<ToDos> post = findById(id);
        post.ifPresent(todoRepository::delete);
    }
}

/*  
    public List<ToDos> getTodosByUser(Long user_id){
        return todoRepository.findByAuthorId(user_id);
    }
	public List<ToDos> getTodosByUserEmail(String email) {
		return todoRepository.findByUserEmail(email);
	}


    public void update(Long id, ToDosRequest request) {
        Optional<ToDos> todo = findById(id);
        if (todo.isPresent()) {
            ToDos forUpdate = todo.get();
            forUpdate.setText(request.getText());
            forUpdate.setFinish(request.isFinished());
            forUpdate.setUserEmail(request.getUserEmail());
            todoRepository.save(forUpdate);
        }
    }

	private Optional<ToDos> findById(Long id) {
        return todoRepository.findById(id);
    }


	public Long addTodo(ToDosRequest request) {
        ToDos todo = new ToDos();
        todo.setUserEmail(request.getUserEmail());
        todo.setText(request.getText());
        todo.setFinish(request.isFinished());
        return todoRepository.save(todo).getId();
	}

    
    public List<ToDos> getAll() {
        return todoRepository.findAll();
    }

    public void delete(Long id) {
        Optional<ToDos> post = findById(id);
        post.ifPresent(todoRepository::delete);
    }

}*/

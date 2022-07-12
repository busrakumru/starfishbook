package de.beuth.starfishbook.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.beuth.starfishbook.model.TodoList;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    TodoList findTodoListById(Long id);

    TodoList findByIdAndTodosId(Long id, Long todoId);

    List<TodoList> findByFinished(boolean finished);

    List<TodoList> findByTodosId(Long todoId);

}

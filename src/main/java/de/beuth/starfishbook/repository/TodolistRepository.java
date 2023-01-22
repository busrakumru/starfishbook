package de.beuth.starfishbook.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.beuth.starfishbook.model.Todolist;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Long> {

    Todolist findTodolistById(Long id);

    Todolist findByIdAndTodosId(Long id, Long todoId);

    List<Todolist> findByFinished(boolean finished);

    List<Todolist> findByTodosId(Long todoId);

}

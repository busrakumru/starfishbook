package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.beuth.starfishbook.model.Todos;

@Repository
public interface TodosRepository extends JpaRepository<Todos, Long> {

    Todos findTodoById(Long id);    
}

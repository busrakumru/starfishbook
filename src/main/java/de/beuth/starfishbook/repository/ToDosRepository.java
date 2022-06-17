package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.beuth.starfishbook.model.ToDos;

@Repository
public interface ToDosRepository extends JpaRepository<ToDos, Long> {

    ToDos findTodoById(Long id);
    
}

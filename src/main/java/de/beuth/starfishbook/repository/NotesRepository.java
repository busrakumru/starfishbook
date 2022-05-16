package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.beuth.starfishbook.entity.Notes;

public interface  NotesRepository extends JpaRepository<Notes, Long> {
  
}


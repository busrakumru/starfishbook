package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.beuth.starfishbook.model.Notes;

@Repository
public interface  NotesRepository extends JpaRepository<Notes, Long> {

   Notes findNotesById(Long id);
   
}


package de.beuth.starfishbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.beuth.starfishbook.model.FileDB;
import de.beuth.starfishbook.model.Notes;

public interface FileDBRepository extends JpaRepository<FileDB, String> {

    //List<FileDB> findByNoteId(Long id);
    //Optional<Notes> findByIdAndNoteId(Long id, Long noteId);

    List<FileDB> findByNotesId(Long notesId);
    
}

package de.beuth.starfishbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.beuth.starfishbook.model.FileDB;

public interface FileDBRepository extends JpaRepository<FileDB, String> {

   List<FileDB> findByNotesId(Long notesId);
    
}

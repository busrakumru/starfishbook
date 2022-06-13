package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.beuth.starfishbook.model.FileDB;

public interface FileDBRepository extends JpaRepository<FileDB, String> {
    
}

package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.beuth.starfishbook.model.Mood;

@Repository
public interface  MoodRepository extends JpaRepository<Mood, Long> {
    Mood findMoodById(Long id);
    
}

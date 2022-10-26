package de.beuth.starfishbook.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.beuth.starfishbook.exception.NoteNotFoundException;
import de.beuth.starfishbook.model.Mood;
import de.beuth.starfishbook.service.MoodService;


@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/users/")

public class MoodController {

    @Autowired
    private MoodService moodService;
  
    // get all notes
    @GetMapping("mood/all")
    public List<Mood> getMood() {
      return this.moodService.getMood();
  
    }
  
    // Get a Single Note
    @GetMapping("moods/{id}")
    public Mood getMoodById(@PathVariable(value = "id") Long moodId)  {
      return this.moodService.findById(moodId);
    }

    @PostMapping("mood")
    public Mood addMood(@RequestBody Mood mood) {
        return this.moodService.addMood(mood);
    }

     
    @DeleteMapping("mood/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.moodService.delete(id);
    }

}


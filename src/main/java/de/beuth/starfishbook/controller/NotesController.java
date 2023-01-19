package de.beuth.starfishbook.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.beuth.starfishbook.exception.NoteNotFoundException;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.CategoriesRepository;
import de.beuth.starfishbook.repository.NotesRepository;
import de.beuth.starfishbook.service.NotesService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/users/")

public class NotesController {

  @Autowired
  private NotesService notesService;

  @GetMapping("notes")
  public List<Notes> getNotes() {
    return this.notesService.getAll();
  }

  @GetMapping("notes/{id}")
  public Notes getNotesById(@PathVariable(value = "id") Long noteId) throws NoteNotFoundException {
    return this.notesService.findById(noteId)
        .orElseThrow(() -> new NoteNotFoundException(noteId));
  }

  @PostMapping("notes")
  public Notes addNotes(@RequestBody Notes notes) {
    return this.notesService.addNotes(notes);
  }

  @DeleteMapping("/notes/{id}")
  public Boolean delete(@PathVariable Long id) {
    return this.notesService.delete(id);
  }

  @PatchMapping("notes/{id}")
  public Notes updateTodoWithMap(@PathVariable(value = "id") Long id, @RequestBody Map<Object, Object> objectMap) {
    return notesService.updateWithMap(id, objectMap);

  }

}
package de.beuth.starfishbook.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import de.beuth.starfishbook.exception.NoteNotFoundException;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.FileDBRepository;
import de.beuth.starfishbook.repository.NotesRepository;
import de.beuth.starfishbook.service.FileStorageService;
import de.beuth.starfishbook.service.NotesService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("auth/users/")
public class NotesController {

  private NotesRepository notesRepository;

  @Autowired
  private NotesService notesService;

  @Autowired
  FileController fileController;

  /*@Autowired
  private FileStorageService storageService;

  @Autowired
  private FileDBRepository fileDBRepository;

  @Autowired
  public NotesController(NotesRepository notesRepository) {
    this.notesRepository = notesRepository;
  }*/


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
  public ResponseEntity<Notes> createNote(@Valid @RequestBody Notes notes)
      throws IOException {
    Notes savedNotes = notesRepository.save(notes);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedNotes.getId()).toUri();

    return ResponseEntity.created(location).body(savedNotes);
  }

  @PutMapping("notes/{id}")
  public Notes updateNote(@PathVariable(value = "id") Long noteId, @RequestBody Notes noteDetails)
      throws NoteNotFoundException {
    Notes updatedNotes = this.notesService.update(noteId, noteDetails);
    return updatedNotes;
  }

  @DeleteMapping("/notes/{id}")
    public Boolean delete(@PathVariable Long id) {
      return this.notesService.delete(id);
  }

  // partially update a Note
  /*
   * @PatchMapping("/notes/{id}/{text}")
   * public Notes updateNotePartially(@PathVariable(value = "id") Long
   * noteId, @PathVariable(value = "text") String text)
   * //try {
   * throws NoteNotFoundException {
   * 
   * Notes notes = this.notesRepository.findById(noteId).get();
   * notes.setText(text);
   * 
   * Notes updatedNote = this.notesRepository.save(notes);
   * 
   * return updatedNote;
   * //return new ResponseEntity<Notes>(notesRepository.save(note),
   * HttpStatus.OK);
   * 
   * }
   */

}
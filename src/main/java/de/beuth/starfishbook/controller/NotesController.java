package de.beuth.starfishbook.controller;

import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.beuth.starfishbook.exception.NoteNotFoundException;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.NotesRepository;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("api/")
public class NotesController {

  @Autowired
  private NotesRepository notesRepository;

  /**
   * public NotesController(NotesRepository notesRepository){
   * this.notesRepository = notesRepository;
   * }
   **/

  // get all notes
  @GetMapping("notes")
  /**
   * public List<Notes> index() {
   * return notesRepository.findAll();
   **/
  public List<Notes> getNotes() {
    return this.notesRepository.findAll();

  }

  // Get a Single Note
  @GetMapping("notes/{id}")
  public Notes getNotesById(@PathVariable(value = "id") Long noteId) throws NoteNotFoundException {
    return this.notesRepository.findById(noteId)
        .orElseThrow(() -> new NoteNotFoundException(noteId));
  }

  // create a new note
  @PostMapping("notes")
  public Notes createNote(@RequestBody Notes note) {
    return this.notesRepository.save(note);
  }

  // Update a Note
  @PutMapping("notes/{id}")
  public Notes updateNote(@PathVariable(value = "id") Long noteId, @RequestBody Notes noteDetail)
      throws NoteNotFoundException {

    Notes notes = this.notesRepository.findById(noteId)
        .orElseThrow(() -> new NoteNotFoundException(noteId));

    notes.setTitle(noteDetail.getTitle());
    notes.setText(noteDetail.getText());
    notes.setColor(noteDetail.getColor());

    Notes updatedNote = this.notesRepository.save(notes);

    return updatedNote;
  }

  //partially update a Note
  /*@PatchMapping("/notes/{id}/{text}")
  public Notes updateNotePartially(@PathVariable(value = "id") Long noteId, @PathVariable(value = "text") String text) 
    //try {
      throws NoteNotFoundException {

      Notes notes = this.notesRepository.findById(noteId).get();
      notes.setText(text);

      Notes updatedNote = this.notesRepository.save(notes);

    return updatedNote;
      //return new ResponseEntity<Notes>(notesRepository.save(note), HttpStatus.OK);
   
  }*/


  // Delete a Note
  @DeleteMapping("/notes/{id}")
  public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) throws NoteNotFoundException {
    Notes notes = this.notesRepository.findById(noteId)
        .orElseThrow(() -> new NoteNotFoundException(noteId));

    this.notesRepository.delete(notes);

    return ResponseEntity.ok().build();
  }

}
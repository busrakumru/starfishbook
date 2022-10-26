package de.beuth.starfishbook.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.NotesRepository;
import de.beuth.starfishbook.request.NotesRequest;

@Service
public class NotesService {

    private final NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Optional<Notes> findById(Long id) {
        return notesRepository.findById(id);
    }

    public Notes save(NotesRequest request) {
        Notes note = new Notes();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setColor(request.getColor());
        note.setFiles(request.getFiles());
        note.setCategories(request.getCategories());

        return notesRepository.save(note);
    }

    public Notes update(Long id, Notes request) {
        Notes note = findNotesById(id);
        
        note.setText(request.getText());
        note.setTitle(request.getTitle());
        note.setColor(request.getColor());

            return notesRepository.save(note);
        
    }

    public List<Notes> getAll() {
        return notesRepository.findAll();
    }

    public  Notes findNotesById(Long id){
        return this.notesRepository.findNotesById(id);
  }

  public Boolean delete(Long id) {
    this.notesRepository.deleteById(id);
    return this.notesRepository.existsById(id);
}
  
}
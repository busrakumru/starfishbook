package de.beuth.starfishbook.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.model.NotesRequest;
import de.beuth.starfishbook.repository.NotesRepository;

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

    public Long save(NotesRequest request) {
        Notes note = new Notes();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setColor(request.getColor());


        return notesRepository.save(note).getId();
    }

    public void update(Long id, NotesRequest request) {
        Optional<Notes> note = findById(id);
        if (note.isPresent()) {
            Notes forUpdate = note.get();
            forUpdate.setText(request.getText());
            forUpdate.setTitle(request.getTitle());
            forUpdate.setColor(request.getColor());

            notesRepository.save(forUpdate);
        }
    }

    public List<Notes> getAll() {
        return notesRepository.findAll();
    }

    public void delete(Long id) {
        Optional<Notes> post = findById(id);
        post.ifPresent(notesRepository::delete);
    }
}
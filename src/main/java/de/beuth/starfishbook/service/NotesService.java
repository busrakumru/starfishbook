package de.beuth.starfishbook.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import java.lang.reflect.Field;
import org.springframework.stereotype.Service;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.NotesRepository;

@Service
public class NotesService {

    @Autowired
    private final NotesRepository notesRepository;


    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Optional<Notes> findById(Long id) {
        return notesRepository.findById(id);
    }

    /*
     * public Notes update(Long id, Notes request) {
     * 
     * Notes note = findNotesById(id);
     * note.setText(request.getText());
     * note.setTitle(request.getTitle());
     * note.setColor(request.getColor());
     * note.setCategories(request.getCategories());
     * return notesRepository.save(note);
     * 
     * }
     */

    public List<Notes> getAll() {
        return notesRepository.findAll();
    }

    public Notes findNotesById(Long id) {
        return this.notesRepository.findNotesById(id);
    }

    public Boolean delete(Long id) {
        this.notesRepository.deleteById(id);
        return this.notesRepository.existsById(id);
    }

    public Notes addNotes(Notes request) {
        Notes note = new Notes();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setColor(request.getColor());
        note.setCategories(request.getCategories());
        return this.notesRepository.save(request);
    }

    public Notes updateWithMap(Long id, Map<Object, Object> objectMap) {

        Optional<Notes> notes = notesRepository.findById(id);
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(Notes.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, notes.get(), value);
        });

        return notesRepository.save(notes.get());
    }

}
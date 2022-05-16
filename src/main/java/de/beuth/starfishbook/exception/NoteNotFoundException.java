package de.beuth.starfishbook.exception;

public class NoteNotFoundException extends Exception {

    private long id;
    
    public NoteNotFoundException(long id) {
    super(String.format("Note is not found with id : '%s'", id));
    }
}

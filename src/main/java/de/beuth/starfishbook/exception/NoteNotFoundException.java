package de.beuth.starfishbook.exception;

public class NoteNotFoundException extends Exception {


    
    public NoteNotFoundException(long id) {
    super(String.format("Note is not found with id : '%s'", id));
    }
}

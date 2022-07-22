package de.beuth.starfishbook.request;

import java.util.HashSet;
import java.util.Set;

import de.beuth.starfishbook.model.FileDB;

public class NotesRequest {

    private Long id;
    private String title;
    private String text;
    private String color;
    private Set<FileDB> files= new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<FileDB> getFiles() {
        return files;
    }

}

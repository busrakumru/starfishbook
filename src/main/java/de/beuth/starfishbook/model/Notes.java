package de.beuth.starfishbook.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "notes")

public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "notes", cascade = CascadeType.ALL)
    //@JsonIgnore
    //private List<FileDB> files;
    private Set<FileDB> files= new HashSet<>();

    public Notes() {
    }

    public Notes(String title, String text, String color, Set<FileDB> files) {
        this.title = title;
        this.text = text;
        this.color = color;
        this.files = files;
    }

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

    /*public List<FileDB> getFiles() {
        return files;
    }

    public void setFiles(List<FileDB> files) {
        this.files = files;
    }*/

    public Set<FileDB> getFiles() {
        return files;
    }

    public void setFiles(Set<FileDB> files) {
        this.files = files;

        for(FileDB file : files) {
            file.setNotes(this);
        }
    }
}
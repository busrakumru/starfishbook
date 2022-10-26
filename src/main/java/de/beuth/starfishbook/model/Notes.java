package de.beuth.starfishbook.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonProperty;


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
    private Set<FileDB> files= new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Categories categories;


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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Set<FileDB> getFiles() {
        return files;
    }

    public void setFiles(Set<FileDB> files) {
        this.files = files;

        for(FileDB file : files) {
            file.setNotes(this);
        }
    }

    /*public Categories getCategories() {
        return categories;
    }
  
    public void setCategories(Categories categories) {
        this.categories = categories;
    }*/
}
package de.beuth.starfishbook.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;



    /*@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notes> notes;
    //private Set<Notes> notes= new HashSet<>();*/

    public Categories() {
    }

    public Categories(String title) {
        this.title = title;
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

    /*public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }*/

    
}

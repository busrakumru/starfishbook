package de.beuth.starfishbook.model;

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

    @Column(name = "img")
    private String img;

    public Notes() {
    }

    public Notes(String title, String text, String color, String img, byte[] pic) {
        this.title = title;
        this.text = text;
        this.color = color;
        this.img = img;
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

    public String getImg(){
        return img;
    }

    public void setImg(String img){
        this.img = img;
    }
}
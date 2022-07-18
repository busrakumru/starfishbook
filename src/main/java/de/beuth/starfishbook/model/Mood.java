package de.beuth.starfishbook.model;

import java.sql.Blob;
import java.util.Date;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "mood")

public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "img")
    private String img;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    @Column(name = "day")
    private String day;

    @Column(name = "daily")
    private String daily;

    @Column(name = "color")
    private String color;







}

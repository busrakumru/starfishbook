package de.beuth.starfishbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "user_id")
    private Long userid;

    
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String emailId;

    @Column(unique = true)
    private String email;

    //@JsonIgnore
    private String password;

    private boolean isEnabled;



}

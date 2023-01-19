package de.beuth.starfishbook.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "todos")
// @JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)

public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "todos", cascade = CascadeType.ALL)
    private Set<Todolist> todolists = new HashSet<>();

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date appointmentTime;

    
      @Column(name = "createdAt")
      private Date createdAt = new Date();

    public Todos(String title, Set<Todolist> todolists, Date appointmentTime, Date createdAt) {
        this.title = title;
        this.createdAt = createdAt;
        this.todolists = todolists;
        this.appointmentTime = appointmentTime;
    }

    public Todos() {
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


    public Date getCreatedAt() {
    return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    }
    
    public Set<Todolist> getTodolist() {
        return todolists;
    }

    public void setTodolist(Set<Todolist> todolists) {
        this.todolists = todolists;

        for (Todolist todolist : todolists) {
            todolist.setTodos(this);
        }

    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}

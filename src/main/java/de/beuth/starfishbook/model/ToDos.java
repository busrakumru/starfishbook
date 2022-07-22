package de.beuth.starfishbook.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "toDos")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)

public class ToDos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "todos", cascade = CascadeType.ALL)
    private Set<TodoList> todolist = new HashSet<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentTime;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    public ToDos(String title, Date createdAt, Set<TodoList> todolist, Date appointmentTime) {
        this.title = title;
        this.createdAt = createdAt;
        this.todolist = todolist;
        this.appointmentTime = appointmentTime;

    }

    public ToDos() {
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

    public Set<TodoList> getTodoList() {
        return todolist;
    }

    public void setTodoList(Set<TodoList> todolist) {
        this.todolist = todolist;

    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
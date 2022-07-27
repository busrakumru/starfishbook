package de.beuth.starfishbook.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "todolist")

public class Todolist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "finished")
    private boolean finished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todos_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Todos todos;

    public Todolist(String text, boolean finished) {
        this.text = text;
        this.finished = false;
    }

    public Todolist() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Todos getTodos() {
        return todos;
    }

    public void setTodos(Todos todos) {
        this.todos = todos;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

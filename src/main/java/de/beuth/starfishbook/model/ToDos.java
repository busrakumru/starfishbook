package de.beuth.starfishbook.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "toDos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)

public class ToDos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "todos")
    //@JoinColumn(name = "todolist")
    List<TodoList> todolist;
    
    @Column(name = "createdAt")
    private Date createdAt = new Date();


     public ToDos( String title, Date createdAt,  List<TodoList>  todolist) {
        this.title=title;
        this.createdAt=createdAt;
        this.todolist=todolist;
    
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

    public  List<TodoList>  getTodoList() {
        return todolist;
    }

    public void setTodoList( List<TodoList>  todolist) {
        this.todolist = todolist;
    }        
}
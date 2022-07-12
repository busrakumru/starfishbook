package de.beuth.starfishbook.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "todoList")

public class TodoList {

    @Id
    @GeneratedValue
    private Long id;

    @Column (name = "text")
    private String text;

    
    @Column (name = "finished")
    private boolean finished;
    
    
    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "todos_id")
    private ToDos todos;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todos_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ToDos todos;



    public TodoList(String text,boolean finished) {
        this.text=text;
        this.finished=false;
    }

	public TodoList() {		
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

	public ToDos getTodos() {
		return todos;
	}

	public void setTodos(ToDos todos) {
		this.todos = todos;
	}

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}

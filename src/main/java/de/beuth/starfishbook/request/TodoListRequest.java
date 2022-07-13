package de.beuth.starfishbook.request;

import de.beuth.starfishbook.model.ToDos;

public class TodoListRequest {

    private Long id;
    private String text;
    private boolean finished;
    private ToDos todos;
   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

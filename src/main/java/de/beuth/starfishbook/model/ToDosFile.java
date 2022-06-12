package de.beuth.starfishbook.model;

import java.util.ArrayList;


public class ToDosFile {


	private ArrayList<ToDos> todoList = new ArrayList<ToDos>();
	
	public ToDosFile() {}
	
	public ToDosFile(ArrayList<ToDos> todoList) {
		this.todoList = todoList;
	}

	public ArrayList<ToDos> getTodoList() {
		return todoList;
	}

	public void setTodoList(ArrayList<ToDos> todoList) {
		this.todoList = todoList;
	}
}

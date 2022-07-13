package de.beuth.starfishbook.request;

import java.util.Date;

public class ToDosRequest {

    private Long id;
    private String title;
    private String text;
    private boolean finished;
    private Date createdAt;
    
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinish(boolean finished) {
        this.finished = finished;
    }

 
}

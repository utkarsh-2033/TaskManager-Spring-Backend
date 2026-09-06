package org.utkarsh.taskmanager.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.utkarsh.taskmanager.model.Task;

import java.util.Date;

public class TasksResponse {
    private String id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Task.Status status;

    private boolean priority;
    private Date dueDate;
    private Date createdAt=new Date();
    private Date updatedAt=new Date();

    public TasksResponse(String id, String title, String description, Task.Status status, boolean priority, Date dueDate, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task.Status getStatus() {
        return status;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

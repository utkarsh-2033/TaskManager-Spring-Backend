package org.utkarsh.taskmanager.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.utkarsh.taskmanager.model.Task;

import java.util.Date;

public class CreateTask {
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Task.Status status;

    private boolean priority;
    private Date dueDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Task.Status getStatus() {
        return status;
    }

    public boolean isPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }
}

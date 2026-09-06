package org.utkarsh.taskmanager.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean priority;
    private Instant dueDate;
    private final Instant createdAt=Instant.now();
    private Instant updatedAt=Instant.now();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    public Task() {
    }

    public Task(String title, String description, Status status, boolean priority, Instant dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isPriority() {
        return priority;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

package org.utkarsh.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utkarsh.taskmanager.model.Task;

public interface TasksRepo extends JpaRepository<Task, String> {
}

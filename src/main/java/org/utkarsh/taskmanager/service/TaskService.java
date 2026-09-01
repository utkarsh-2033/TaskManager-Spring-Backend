package org.utkarsh.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utkarsh.taskmanager.dto.CreateTask;
import org.utkarsh.taskmanager.model.Task;
import org.utkarsh.taskmanager.repository.TasksRepo;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TasksRepo tasksRepo;

    public Task addTask(CreateTask task) {
        Task newtask= new Task(task.getTitle(), task.getDescription(), task.getStatus(), task.isPriority(), task.getDueDate());
        tasksRepo.save(newtask);
        
        return newtask;
    }

    public List<Task> getAllTasks() {
        return tasksRepo.findAll();
    }

    public Task getTask(String id) {
        return tasksRepo.findById(id).orElse(null);
    }

    public Task updateTask(String id, Task task) {

        Task t=null;
        if (tasksRepo.existsById(id)) {
            t = tasksRepo.save(task);
        }
        return t;

    }
    public boolean deleteTask(String id){
        if(tasksRepo.existsById((id))){
            return false;
        }
            tasksRepo.deleteById((id));
        return true;
    }
}

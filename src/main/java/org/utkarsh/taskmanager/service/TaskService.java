package org.utkarsh.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utkarsh.taskmanager.dto.CreateTask;
import org.utkarsh.taskmanager.dto.TasksResponse;
import org.utkarsh.taskmanager.dto.UpdateTask;
import org.utkarsh.taskmanager.model.Task;
import org.utkarsh.taskmanager.model.User;
import org.utkarsh.taskmanager.repository.TasksRepo;
import org.utkarsh.taskmanager.repository.UserRepo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TasksRepo tasksRepo;
    @Autowired
    private UserRepo userRepo;

    public Task addTask(CreateTask task , String username) {
        User user=userRepo.findByUsername(username);
        Task newtask= new Task(task.getTitle(), task.getDescription(), task.getStatus(), task.isPriority(), task.getDueDate());
        newtask.setUser(user);
        tasksRepo.save(newtask);
        
        return newtask;
    }

    public List<TasksResponse> getAllTasks(String username) {
        User user=userRepo.findByUsername(username);
        List<Task> userTasks = user.getTasks();
        List<TasksResponse> res=new ArrayList<>();
        for (Task t : userTasks) {
            res.add(new TasksResponse(
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getStatus(),
                    t.isPriority(),
                    t.getDueDate(),
                    t.getCreatedAt(),
                    t.getUpdatedAt()));
        }
        return res;
    }

    public TasksResponse getTask(String id , String username) {
        Task task=tasksRepo.findById(id).orElse(null);
        System.out.println(username);
        if(task!=null && task.getUser().getUsername().equals(username)) {
            TasksResponse res=new TasksResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.isPriority(),
                    task.getDueDate(),
                    task.getCreatedAt(),
                    task.getUpdatedAt());
            return res;
        }
        return null;
    }

    public TasksResponse updateTask(String id, UpdateTask task , String username) {
//      User user=userRepo.findByUsername(username);
        Task t = tasksRepo.findById(id).orElse(null);
        if (t != null && t.getUser().getUsername().equals(username)) {
//          t.setUser(user);
            t.setTitle(task.getTitle());
            t.setDescription(task.getDescription());
            t.setPriority(task.isPriority());
            t.setStatus(task.getStatus());
            t.setDueDate(task.getDueDate());
            t.setUpdatedAt(Instant.now());
            tasksRepo.save(t);

            return new TasksResponse(
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getStatus(),
                    t.isPriority(),
                    t.getDueDate(),
                    t.getCreatedAt(),
                    t.getUpdatedAt());
        }
        return null;

    }
    public boolean deleteTask(String id , String username){
//      User user=userRepo.findByUsername(username);
        Task t = tasksRepo.findById(id).orElse(null);
        if (t != null && t.getUser().getUsername().equals(username)) {
            tasksRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

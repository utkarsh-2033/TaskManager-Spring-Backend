package org.utkarsh.taskmanager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.utkarsh.taskmanager.dto.CreateTask;
import org.utkarsh.taskmanager.dto.TasksResponse;
import org.utkarsh.taskmanager.dto.UpdateTask;
import org.utkarsh.taskmanager.model.Task;
import org.utkarsh.taskmanager.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/task")
    public ResponseEntity<String> addTask(@RequestBody CreateTask task ,
                                          @AuthenticationPrincipal UserDetails userDetails) {
//        System.out.println("Task received: " + task );
        String username=userDetails.getUsername();
        Task newtask=service.addTask(task , username);
//        System.out.println("New task created: " + newtask );
        if (newtask!=null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TasksResponse>> getAllTasks(@AuthenticationPrincipal UserDetails userDetails){
        String username=userDetails.getUsername();
        List<TasksResponse> tasks=service.getAllTasks(username);
//        System.out.println("Tasks retrieved: " + tasks);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TasksResponse> getTask(@PathVariable String id ,
                                        @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails.getUsername());
        TasksResponse task=service.getTask(id , userDetails.getUsername());
        if (task!=null){
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TasksResponse> updateTask(@PathVariable String id,
                                           @RequestBody UpdateTask task ,
                                           @AuthenticationPrincipal UserDetails userDetails){
        TasksResponse updatedTask=service.updateTask(id, task , userDetails.getUsername());
        if (updatedTask!=null){
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable String id ,
                                                 @AuthenticationPrincipal UserDetails userDetails){
        boolean deleted=service.deleteTask(id , userDetails.getUsername());
        if(deleted) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }


}

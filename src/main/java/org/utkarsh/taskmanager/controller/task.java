package org.utkarsh.taskmanager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utkarsh.taskmanager.dto.CreateTask;
import org.utkarsh.taskmanager.model.Task;
import org.utkarsh.taskmanager.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class task {

    @Autowired
    private final TaskService service;

    public task(TaskService service) {
        this.service = service;
    }

    @PostMapping("/task")
    public ResponseEntity<String> addTask(@RequestBody CreateTask task) {
//        System.out.println("Task received: " + task );
        Task newtask=service.addTask(task);
//        System.out.println("New task created: " + newtask );
        if (newtask!=null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks=service.getAllTasks();
        System.out.println("Tasks retrieved: " + tasks);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id){
        Task task=service.getTask(id);
        if (task!=null){
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task){
        Task updatedTask=service.updateTask(id, task);
        if (updatedTask!=null){
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable String id){
        boolean deleted=service.deleteTask((id));
        if(deleted) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }


}

/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: REST API Controller for Task management operations
 */
package com.snhu.cs320.controller;

import TaskService.Task;
import TaskService.TaskService;
import com.snhu.cs320.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController() {
        this.taskService = new TaskService();
    }
    
    /**
     * Create a new task
     * POST /api/tasks
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            Task task = new Task(
                taskDTO.getTaskID(),
                taskDTO.getName(),
                taskDTO.getDescription()
            );
            taskService.addTask(task);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task created successfully");
            response.put("taskID", taskDTO.getTaskID());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Update task name
     * PUT /api/tasks/{taskID}/name
     */
    @PutMapping("/{taskID}/name")
    public ResponseEntity<Map<String, String>> updateName(
            @PathVariable String taskID, 
            @RequestBody Map<String, String> request) {
        try {
            String name = request.get("name");
            taskService.updateName(taskID, name);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task name updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Update task description
     * PUT /api/tasks/{taskID}/description
     */
    @PutMapping("/{taskID}/description")
    public ResponseEntity<Map<String, String>> updateDescription(
            @PathVariable String taskID, 
            @RequestBody Map<String, String> request) {
        try {
            String description = request.get("description");
            taskService.updateDescription(taskID, description);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task description updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Delete a task
     * DELETE /api/tasks/{taskID}
     */
    @DeleteMapping("/{taskID}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable String taskID) {
        try {
            taskService.deleteTask(taskID);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}

/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: REST Controller for Task management with CRUD operations
 */
package api.controller;

import TaskService.Task;
import TaskService.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController() {
        this.taskService = new TaskService();
    }
    
    /**
     * GET /api/tasks - Get all tasks
     */
    @GetMapping
    public ResponseEntity<Collection<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    /**
     * GET /api/tasks/{id} - Get a specific task by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        try {
            Task task = taskService.getTask(id);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * POST /api/tasks - Create a new task
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest request) {
        try {
            Task task = new Task(
                request.getTaskID(),
                request.getName(),
                request.getDescription()
            );
            taskService.addTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * PUT /api/tasks/{id} - Update an existing task
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable String id,
            @RequestBody TaskUpdateRequest request) {
        try {
            if (request.getName() != null) {
                taskService.updateName(id, request.getName());
            }
            if (request.getDescription() != null) {
                taskService.updateDescription(id, request.getDescription());
            }
            Task updatedTask = taskService.getTask(id);
            return ResponseEntity.ok(updatedTask);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE /api/tasks/{id} - Delete a task
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Inner classes for request DTOs
    public static class TaskRequest {
        private String taskID;
        private String name;
        private String description;
        
        public String getTaskID() { return taskID; }
        public void setTaskID(String taskID) { this.taskID = taskID; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
    
    public static class TaskUpdateRequest {
        private String name;
        private String description;
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}

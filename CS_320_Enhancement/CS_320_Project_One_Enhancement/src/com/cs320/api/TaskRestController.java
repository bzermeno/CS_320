/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026 
 *  Course ID: CS-320
 *  Description: REST API controller for Task management operations.
 *  Provides HTTP endpoints for creating, updating, and deleting tasks.
 *  Accepts and returns JSON-formatted data.
 */
package com.cs320.api;

import TaskService.Task;
import TaskService.TaskController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller exposing HTTP endpoints for Task operations.
 * Maps HTTP requests to TaskController methods and returns JSON responses.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {
    
    private final TaskController taskController;
    
    /**
     * Constructor initializes the controller layer.
     */
    public TaskRestController() {
        this.taskController = new TaskController();
    }
    
    /**
     * Creates a new task.
     * 
     * POST /api/tasks
     * Request Body: { "taskID": "123", "name": "Task Name", "description": "Task Description" }
     * 
     * @param taskData Map containing task information
     * @return ResponseEntity with success or error message
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@RequestBody Map<String, String> taskData) {
        try {
            // Validate request body
            if (taskData == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Error: Request body cannot be null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            String taskID = taskData.get("taskID");
            String name = taskData.get("name");
            String description = taskData.get("description");
            
            // Validate required fields
            if (taskID == null || name == null || description == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Error: Missing required fields (taskID, name, description)");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            String result = taskController.createTask(taskID, name, description);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            response.put("taskID", taskID);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error creating task: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Deletes a task by ID.
     * 
     * DELETE /api/tasks/{taskID}
     * 
     * @param taskID The unique identifier of the task
     * @return ResponseEntity with success or error message
     */
    @DeleteMapping("/{taskID}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable String taskID) {
        try {
            String result = taskController.deleteTask(taskID);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error deleting task: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Updates a task's name.
     * 
     * PUT /api/tasks/{taskID}/name
     * Request Body: { "name": "New Task Name" }
     * 
     * @param taskID The unique identifier of the task
     * @param requestData Map containing the new name
     * @return ResponseEntity with success or error message
     */
    @PutMapping("/{taskID}/name")
    public ResponseEntity<Map<String, String>> updateName(
            @PathVariable String taskID, 
            @RequestBody Map<String, String> requestData) {
        try {
            String newName = requestData.get("name");
            String result = taskController.updateTaskName(taskID, newName);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating task name: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Updates a task's description.
     * 
     * PUT /api/tasks/{taskID}/description
     * Request Body: { "description": "New Description" }
     * 
     * @param taskID The unique identifier of the task
     * @param requestData Map containing the new description
     * @return ResponseEntity with success or error message
     */
    @PutMapping("/{taskID}/description")
    public ResponseEntity<Map<String, String>> updateDescription(
            @PathVariable String taskID, 
            @RequestBody Map<String, String> requestData) {
        try {
            String newDescription = requestData.get("description");
            String result = taskController.updateTaskDescription(taskID, newDescription);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating task description: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

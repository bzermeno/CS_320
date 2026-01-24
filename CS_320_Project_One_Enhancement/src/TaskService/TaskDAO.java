/*  Author Name: Beau Zermeno
 *  Date: January 24, 2026 
 *  Course ID: CS-320
 *  Description: TaskDAO (Data Access Object) class handles all data persistence operations
 *  for Task objects. This layer encapsulates the data storage implementation (HashMap)
 *  and provides CRUD operations, following the separation of concerns principle.
 *  
 *  Purpose: Isolates data access logic from business logic, making the system more maintainable
 *  and allowing for easier changes to the underlying data storage mechanism without affecting
 *  other layers.
 */
package TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Data Access Object for Task entities.
 * Handles all database/storage operations for tasks.
 */
public class TaskDAO {
    // HashMap to hold Task objects (in-memory storage)
    private final Map<String, Task> tasks = new HashMap<>();
    
    /**
     * Saves a new task to the data store.
     * 
     * @param task The Task object to save
     * @throws IllegalArgumentException if task is null or ID already exists
     */
    public void save(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null.");
        }
        if (tasks.containsKey(task.getTaskID())) {
            throw new IllegalArgumentException("Task must have a unique ID.");
        }
        tasks.put(task.getTaskID(), task);
    }
    
    /**
     * Updates an existing task in the data store.
     * 
     * @param task The Task object with updated information
     * @throws IllegalArgumentException if task is null or doesn't exist
     */
    public void update(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null.");
        }
        if (!tasks.containsKey(task.getTaskID())) {
            throw new IllegalArgumentException("Task ID not found");
        }
        tasks.put(task.getTaskID(), task);
    }
    
    /**
     * Deletes a task from the data store by ID.
     * 
     * @param taskID The unique identifier of the task to delete
     * @throws IllegalArgumentException if taskID doesn't exist
     */
    public void delete(String taskID) {
        if (!tasks.containsKey(taskID)) {
            throw new IllegalArgumentException("Task ID not found");
        }
        tasks.remove(taskID);
    }
    
    /**
     * Finds a task by its unique ID.
     * 
     * @param taskID The unique identifier of the task
     * @return Optional containing the Task if found, empty Optional otherwise
     */
    public Optional<Task> findById(String taskID) {
        return Optional.ofNullable(tasks.get(taskID));
    }
    
    /**
     * Checks if a task with the given ID exists.
     * 
     * @param taskID The unique identifier to check
     * @return true if task exists, false otherwise
     */
    public boolean exists(String taskID) {
        return tasks.containsKey(taskID);
    }
}

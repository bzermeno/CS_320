/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: TaskRepository class handles data access operations for Task objects.
 *  This is the data access layer in the layered architecture.
 */
package TaskService;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository {
    // HashMap to hold Task objects
    private final Map<String, Task> tasks = new HashMap<>();
    
    /**
     * Adds a task to the repository
     * @param task The task to add
     */
    public void save(Task task) {
        tasks.put(task.getTaskID(), task);
    }
    
    /**
     * Finds a task by ID
     * @param taskID The ID of the task to find
     * @return The task if found, null otherwise
     */
    public Task findById(String taskID) {
        return tasks.get(taskID);
    }
    
    /**
     * Checks if a task exists by ID
     * @param taskID The ID to check
     * @return true if task exists, false otherwise
     */
    public boolean existsById(String taskID) {
        return tasks.containsKey(taskID);
    }
    
    /**
     * Deletes a task by ID
     * @param taskID The ID of the task to delete
     */
    public void deleteById(String taskID) {
        tasks.remove(taskID);
    }
}

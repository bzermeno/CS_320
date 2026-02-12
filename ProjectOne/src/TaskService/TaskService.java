/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: TaskService class allows user to add Task objects to the HashMap tasks, delete
 *  a task identified by taskID, and edit task fields, except for taskID, which is immutable.
 */
package TaskService;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
	// HashMap to hold Task objects
    private final Map<String, Task> tasks = new HashMap<>();
    
    public void addTask (Task task) {
        
    	if (task == null) {
            throw new IllegalArgumentException("Task must be not be null.");
        }
        if (tasks.containsKey(task.getTaskID())) {
        	throw new IllegalArgumentException("Task must have a unique ID.");
        }
        tasks.put(task.getTaskID(), task);
    }

    public void deleteTask(String taskID) {
        // contactID does not exist
    	if (!tasks.containsKey(taskID)) {
            throw new IllegalArgumentException("Task ID not found");
        }
        tasks.remove(taskID);
    }

    // Functions allow user to assign new values to task fields.
    public void updateName(String taskID, String name) {
        Task task = getTask(taskID);
        task.setName(name);
    }

    public void updateDescription(String taskID, String description) {
        Task task = getTask(taskID);
        task.setDescription(description);
    }
    
    private Task getTask(String taskID) {
        Task task = tasks.get(taskID);
        if (task == null) {
            throw new IllegalArgumentException("Task ID not found");
        }
        return task;
    }
}

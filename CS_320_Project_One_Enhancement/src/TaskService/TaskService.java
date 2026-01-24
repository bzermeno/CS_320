/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: TaskService class contains business logic for managing Task objects.
 *  This service layer delegates data persistence operations to TaskDAO, following the
 *  layered architecture pattern. It focuses on business rules, validation, and coordination
 *  between the Controller and DAO layers.
 *  
 *  Purpose: Implements core business logic and rules for task management. This layer ensures
 *  consistent application logic and provides an abstraction between the Controller and DAO layers.
 *  Updated: January 24, 2026
 */
package TaskService;

/**
 * Service layer for Task business logic.
 * Coordinates between Controller and DAO layers, focusing on business rules.
 */
public class TaskService {
    // DAO instance for data access operations
    private final TaskDAO taskDAO;
    
    /**
     * Constructor with dependency injection for DAO layer.
     * 
     * @param taskDAO The data access object for task operations
     */
    public TaskService(TaskDAO taskDAO) {
        if (taskDAO == null) {
            throw new IllegalArgumentException("TaskDAO must not be null.");
        }
        this.taskDAO = taskDAO;
    }
    
    /**
     * Default constructor that creates its own DAO instance.
     * Useful for backward compatibility with existing tests.
     */
    public TaskService() {
        this.taskDAO = new TaskDAO();
    }
    
    /**
     * Adds a new task to the system.
     * Business logic: Validates task and ensures unique ID before saving.
     * 
     * @param task The Task object to add
     * @throws IllegalArgumentException if task is null or task ID already exists
     */
    public void addTask (Task task) {
        // Business rule: Task must not be null
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null.");
        }
        // Delegate to DAO layer for persistence
        taskDAO.save(task);
    }

    /**
     * Deletes a task from the system.
     * Business logic: Verifies task exists before deletion.
     * 
     * @param taskID The unique identifier of the task to delete
     * @throws IllegalArgumentException if taskID doesn't exist
     */
    public void deleteTask(String taskID) {
        // Delegate to DAO layer for deletion
        taskDAO.delete(taskID);
    }

    /**
     * Updates the name of a task.
     * Business logic: Retrieves task, applies change, and persists update.
     * 
     * @param taskID The unique identifier of the task
     * @param name The new name value
     * @throws IllegalArgumentException if taskID doesn't exist or validation fails
     */
    public void updateName(String taskID, String name) {
        Task task = getTask(taskID);
        task.setName(name);
        taskDAO.update(task);
    }

    /**
     * Updates the description of a task.
     * Business logic: Retrieves task, applies change, and persists update.
     * 
     * @param taskID The unique identifier of the task
     * @param description The new description value
     * @throws IllegalArgumentException if taskID doesn't exist or validation fails
     */
    public void updateDescription(String taskID, String description) {
        Task task = getTask(taskID);
        task.setDescription(description);
        taskDAO.update(task);
    }
    
    /**
     * Helper method to retrieve a task by ID.
     * Business logic: Ensures task exists before returning.
     * 
     * @param taskID The unique identifier of the task
     * @return The Task object
     * @throws IllegalArgumentException if taskID doesn't exist
     */
    private Task getTask(String taskID) {
        return taskDAO.findById(taskID)
            .orElseThrow(() -> new IllegalArgumentException("Task ID not found"));
    }
}

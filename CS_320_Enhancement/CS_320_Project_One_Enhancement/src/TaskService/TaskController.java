/*  Author Name: Beau Zermeno
 *  Date: January 24, 2026 
 *  Course ID: CS-320
 *  Description: TaskController handles incoming requests/user interactions for task management.
 *  This controller layer is responsible for input validation, request handling, and delegating
 *  business logic to the TaskService layer. It follows the Model-View-Controller (MVC) pattern.
 *  
 *  Purpose: Provides a clear entry point for task operations, handling user input/output and
 *  delegating to the service layer. This separation allows for different interfaces (CLI, GUI, REST API)
 *  to be easily implemented without changing business logic.
 */
package TaskService;

/**
 * Controller layer for Task operations.
 * Handles user input validation and delegates to service layer.
 */
public class TaskController {
    private final TaskService taskService;
    
    /**
     * Constructor with dependency injection for service layer.
     * 
     * @param taskService The service layer instance for business logic
     */
    public TaskController(TaskService taskService) {
        if (taskService == null) {
            throw new IllegalArgumentException("TaskService must not be null.");
        }
        this.taskService = taskService;
    }
    
    /**
     * Default constructor that creates its own service instance.
     */
    public TaskController() {
        this.taskService = new TaskService();
    }
    
    /**
     * Handles request to create a new task.
     * Validates inputs and delegates to service layer.
     * 
     * @param taskID Unique identifier for the task
     * @param name Name of the task
     * @param description Description of the task
     * @return Success message or error details
     */
    public String createTask(String taskID, String name, String description) {
        try {
            // Create task with validation happening in the Task constructor
            Task task = new Task(taskID, name, description);
            
            // Delegate to service layer
            taskService.addTask(task);
            
            return "Task created successfully with ID: " + taskID;
        } catch (Exception e) {
            return "Error creating task: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to delete a task.
     * 
     * @param taskID The unique identifier of the task to delete
     * @return Success message or error details
     */
    public String deleteTask(String taskID) {
        try {
            // Input validation
            if (taskID == null || taskID.isEmpty()) {
                return "Error: Task ID cannot be null or empty";
            }
            
            // Delegate to service layer
            taskService.deleteTask(taskID);
            
            return "Task deleted successfully: " + taskID;
        } catch (Exception e) {
            return "Error deleting task: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to update a task's name.
     * 
     * @param taskID The unique identifier of the task
     * @param newName The new name value
     * @return Success message or error details
     */
    public String updateTaskName(String taskID, String newName) {
        try {
            // Input validation
            if (taskID == null || taskID.isEmpty()) {
                return "Error: Task ID cannot be null or empty";
            }
            
            // Delegate to service layer
            taskService.updateName(taskID, newName);
            
            return "Task name updated successfully";
        } catch (Exception e) {
            return "Error updating task name: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to update a task's description.
     * 
     * @param taskID The unique identifier of the task
     * @param newDescription The new description value
     * @return Success message or error details
     */
    public String updateTaskDescription(String taskID, String newDescription) {
        try {
            // Input validation
            if (taskID == null || taskID.isEmpty()) {
                return "Error: Task ID cannot be null or empty";
            }
            
            // Delegate to service layer
            taskService.updateDescription(taskID, newDescription);
            
            return "Task description updated successfully";
        } catch (Exception e) {
            return "Error updating task description: " + e.getMessage();
        }
    }
}

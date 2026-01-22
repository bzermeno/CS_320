/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: TaskService class allows user to add Task objects, delete
 *  a task identified by taskID, and edit task fields, except for taskID, which is immutable.
 *  This is the service layer in the layered architecture that uses TaskRepository for data access.
 */
package TaskService;

public class TaskService {
    // Repository for data access
    private final TaskRepository taskRepository;
    
    public TaskService() {
        this.taskRepository = new TaskRepository();
    }
    
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public void addTask (Task task) {
        
    	if (task == null) {
            throw new IllegalArgumentException("Task must be not be null.");
        }
        if (taskRepository.existsById(task.getTaskID())) {
        	throw new IllegalArgumentException("Task must have a unique ID.");
        }
        taskRepository.save(task);
    }

    public void deleteTask(String taskID) {
        // contactID does not exist
    	if (!taskRepository.existsById(taskID)) {
            throw new IllegalArgumentException("Task ID not found");
        }
        taskRepository.deleteById(taskID);
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
        Task task = taskRepository.findById(taskID);
        if (task == null) {
            throw new IllegalArgumentException("Task ID not found");
        }
        return task;
    }
}

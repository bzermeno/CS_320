/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: Task class allows user to create a Task object with a unique taskID,
 *  with name and description fields. The fields must meet the given constraints.
 *  Updated: January 20, 2026 
 */
package TaskService;

public class Task {
	private final String taskID;
	private String name;
	private String description;
	
	// Initialize length constraints
	private final int MAX_TASKID_LENGTH = 10;
	private final int MAX_NAME_LENGTH = 20;
	private final int MAX_DESCRIPTION_LENGTH = 50;
	
	// Constructor with field validation
	public Task (String taskID, String name, String description) {
		if (taskID == null) {
			throw new NullPointerException("taskID cannot be null");
		}
		if (taskID.length() > MAX_TASKID_LENGTH) {
			throw new IllegalArgumentException("taskID length cannot exceed 10 characters");
		}
		if (name == null) {
			throw new NullPointerException("name cannot be null");
		}
		if (name == null || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("name length cannot exceed 20 characters");
		}
		if (description == null) {
			throw new NullPointerException("description cannot be null");
		}
		if (description.length() > MAX_DESCRIPTION_LENGTH) {
			throw new IllegalArgumentException("description length cannot exceed 50 characters");
		}
		this.taskID = taskID;
		this.name = name;
		this.description = description;		
	}
	
	// Getter functions
	public String getTaskID() {
			return taskID;
		}
	public String getName() {
		return name;
	}
	
	public String getDescription() {
			return description;
	}
	
	// Setter functions with field validation
	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException("name cannot be null");
		}
		if (name == null || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("name length cannot exceed 20 characters");
		}
		this.name = name;
	}

	public void setDescription(String description) {
		if (description == null) {
			throw new NullPointerException("description cannot be null");
		}
		if (description.length() > MAX_DESCRIPTION_LENGTH) {
			throw new IllegalArgumentException("description length cannot exceed 50 characters");
		}
		this.description = description;
	}

}

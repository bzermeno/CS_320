/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: Task class allows user to create a Task object with a unique taskID,
 *  with name and description fields. The fields must meet the given constraints. 
 */
package TaskService;

public class Task {
	private final String taskID;
	private String name;
	private String description;
	
	// Constructor with field validation
	public Task (String taskID, String name, String description) {
		if (taskID == null || taskID.length() > 10) {
			throw new IllegalArgumentException("Invalid ID");
		}
		if (name == null || name.length() > 20) {
			throw new IllegalArgumentException("Invalid name");
		}
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description");
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
		if (name == null || name.length() > 20) {
			throw new IllegalArgumentException("Invalid name");
		}
		this.name = name;
	}

	public void setDescription(String description) {
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description");
		}
		this.description = description;
	}

}

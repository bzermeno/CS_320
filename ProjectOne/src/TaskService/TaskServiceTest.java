/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: TaskServiceTest class tests that tasks are successfully added to the HashMap
 *  task, tasks are deleted successfully, and that attempts to add invalid tasks throw
 *  the proper exceptions.
 */
package TaskService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
	private TaskService taskService;
    private Task task;

    @BeforeEach
    public void newTaskService() {
        taskService = new TaskService();
        task = new Task("12345", "Setup", "Setup task");
        taskService.addTask(task);
    }

    @Test
    public void testAddTaskSuccessfully() {
        // Add a new Task object with valid fields to tasks
    	Task newTask = new Task("54321", "Cleanup", "Cleanup area");
        taskService.addTask(newTask);
        assertEquals(newTask.getName(), "Cleanup");
    }

    @Test
    public void testAddTaskWithDuplicateId() {
    	// taskID already exists
        assertThrows(IllegalArgumentException.class, () -> {
            Task duplicateTask = new Task("12345", "Setup", "Setup task");
            taskService.addTask(duplicateTask);
        });
    }

    @Test
    public void testDeleteTaskSuccessfully() {
    	// task exists
        taskService.deleteTask("12345");
        // task does not exist
        assertThrows(IllegalArgumentException.class, () -> taskService.deleteTask("12345"));
    }

    @Test
    public void testUpdateName() {
        taskService.updateName("12345", "Do nothing");
        assertEquals(task.getName(), "Do nothing");
    }

    @Test
    public void testUpdateDescription() {
        taskService.updateDescription("12345", "Do nothing");
        assertEquals(task.getDescription(), "Do nothing");
    }
    
    @Test
    public void testUpdateNameWithInvalidFields() {
        // name is null
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateName("12345", null);
        });
        // name is too long
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateName("12345", "Setup task 1234512345");
        });
    }

    @Test
	public void testUpdateDescriptionWithInvalidFields() {
		// description is null
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateDescription("12345", null);
        });
        // description is too long
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateDescription("12345", "Setup task 1234567890 1234567890 1234567890 1234567890");
        });
	}

}

/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: TaskTest class tests that a Task object with valid fields is created successfully,
 *  and that attempting to create an object with invalid fields throws the proper exceptions.
 */
package TaskService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
	private Task task;
	
	@BeforeEach
	void newTask() {
		// Create Task object with valid fields
		task = new Task("100", "Setup", "Setup task");
	}
	
	@Test
	void testValidContact() {
		assertEquals("100", task.getTaskID());
		assertEquals("Setup", task.getName());
		assertEquals("Setup task", task.getDescription());
	}

	@Test
	void testInvalidTaskID() {
		// taskID is null
		assertThrows(NullPointerException.class, () -> {
			new Task(null, "Setup", "Setup task");
        });
		// taskID is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Task("12345678901", "Setup", "Setup task");
        });
	}
	
	@Test
	void testInvalidName() {
		// name is null
		assertThrows(NullPointerException.class, () -> {
			new Task("100", null, "Setup task");
        });
		// name is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Task("100", "Setup task 1234567890", "Setup task");
        });
	}
	
	@Test
	void testInvalidLastName() {
		// lastName is null
		assertThrows(NullPointerException.class, () -> {
			new Task("100", "Setup", null);
        });
		// lastName is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Task("100", "Setup", "setup task 1234567890 1234567890 1234567890 1234567890");
        });
	}
	
	@Test
	void testSetName() {
		// Assign name with new valid attribute
		task.setName("Cleanup");
		assertEquals("Cleanup", task.getName());
		// name is null
		assertThrows(NullPointerException.class, () -> {
			task.setName(null);
        });
		// name is too long
		assertThrows(IllegalArgumentException.class, () -> {
			task.setName("Setup task 1234567890");
        });
	}
	
	@Test
	void testSetDescription() {
		// Assign description with new valid attribute
		task.setDescription("Cleanup area");
		assertEquals("Cleanup area", task.getDescription());
		// decription is null
		assertThrows(NullPointerException.class, () -> {
			task.setDescription(null);
        });
		// lastName is too long
		assertThrows(IllegalArgumentException.class, () -> {
			task.setDescription("Cleanup area 1234567890 1234567890 1234567890 1234567890");
        });
	}	

}

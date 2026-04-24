package tests;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import taskservice.TaskService;

public class TaskServiceTest {
	
	private TaskService service;
	
	// Initialize TaskService object used in subsequent tests
	@BeforeEach
	void setupService() {
		service = new TaskService();
	}
	
	// Verify invalid IDs are rejected
	@ParameterizedTest
	@ValueSource (strings = {"", "01234567891"})
	@NullSource
	void testAddInvalidID(String id) {
		assertThrows(IllegalArgumentException.class, () ->{
			service.addTask(id, "Valid Name", "Valid description");
		});
	}
	
	// Verify valid task objects can be added to in-memory storage
	@Test
	void testAddValidTask() {
		service.addTask("0123456", "Dylan's Task", "This is just a fun little task.");
		service.addTask("6543210", "Alex's Task", "A new season of The Expanse would be so nice.");
		
		assertEquals("0123456", service.findTask("0123456").getID());
		assertEquals("Dylan's Task", service.findTask("0123456").getName());
		
		assertEquals("6543210", service.findTask("6543210").getID());
		assertEquals("Alex's Task", service.findTask("6543210").getName());
	}
	
	// Verify tasks with duplicate IDs cannot be added
	@Test
	void testDuplicateTaskID() {
		service.addTask("0123456", "Dylan's Task", "This is just a fun little task.");
		
		assertThrows(IllegalArgumentException.class, () ->{
			service.addTask("0123456", "Dylan's Task", "This is just a fun little task.");
		});
	}
	
	// Verify tasks are deleted correctly by ID
	@ParameterizedTest
	@ValueSource(strings = {"0123456", "6543210"})
	void testDeleteTask(String id) {
		service.addTask("0123456", "Dylan's Task", "This is just a fun little task.");
		service.addTask("6543210", "Alex's Task", "A new season of The Expanse would be so nice.");
		
		service.deleteTask(id);
		
		assertThrows(IllegalArgumentException.class, () ->{
			service.findTask(id);
		});
	}
	
	// Verify non-existent tasks cannot be deleted
	@Test
	void testDeleteNonExistTask() {
		assertThrows(IllegalArgumentException.class, () ->{
			service.deleteTask("0123");
		});
	}
	
	// Verify task names are updated appropriately 
	@ParameterizedTest
	@ValueSource(strings = {"New Name", "Try this one"})
	void testUpdateTaskName(String name) {
		service.addTask("0123456", "Dylan's Task", "This is just a fun little task.");
		
		service.updateTaskName("0123456", name);
		
		assertEquals(name, service.findTask("0123456").getName());
	}
	
	@Test
	void testUpdateNonExistName() {
		assertThrows(IllegalArgumentException.class, () ->{
			service.updateTaskName("0123", "That's a name");
		});
	}
	
	// Verify task descriptions are updated appropriately
	@ParameterizedTest
	@ValueSource (strings = {"This is a new description", "This is a newer description"})
	void testUpdateTaskDescription(String description) {
		service.addTask("0123456", "Dylan's Task", "This is just a fun little task.");
		
		service.updateTaskDescription("0123456", description);
		
		assertEquals(description, service.findTask("0123456").getDescription());
	}
	
	// Verify non-existent task descriptions cannot be updated
	@Test
	void testUpdateNonExistDescription() {
		assertThrows(IllegalArgumentException.class, () ->{
			service.updateTaskDescription("0123", "That's a description.");
		});
	}
	
}

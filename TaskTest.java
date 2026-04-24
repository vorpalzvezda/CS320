package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import task.Task;

public class TaskTest {
	
	// Verify task attributes can be instantiated correctly
	@Test
	void testValidTask() {
		Task task = new Task("023", "testTask", "This is a basic task");
		assertEquals("023", task.getID());
		assertEquals("testTask", task.getName());
		assertEquals("This is a basic task", task.getDescription());
	}
	
	// Verify ID length constraint
	@Test
	void testMaxIdLength() {
		Task task = new Task("0123456789", "testTask", "This is a basic task");
		assertEquals("0123456789", task.getID());
	}
	
	// Verify task name constraint
	@Test
	void testMaxNameLength() {
		String taskName = "a".repeat(20);
		
		Task task = new Task("023", taskName, "This is a basic task");
		assertEquals(taskName, task.getName());
	}
	
	// Verify task description constraint
	@Test
	void testMaxDescriptionLength() {
		
		String info = "a".repeat(50); // Length of 50 chars
		
		Task task = new Task("023", "testTask", info);
		assertEquals(info,task.getDescription());
	}
	
	// Verify task ID constraints
	@ParameterizedTest
	@ValueSource(strings = {"123456789101", ""})
	@NullSource
	void parameterizedIdTest(String id) {
		assertThrows(IllegalArgumentException.class, () ->{
			new Task(id, "Dylan's Task", "This is just some info");
		});
	}
	
	// Verify task name constraints
	@ParameterizedTest
	@ValueSource(strings = {"ThisIsEntirelyTooManyLettersWhatWasYourCPUThinking",
			"Dylan Wienecke1516121", ""})
	@NullSource
	void parameterizedNameTest(String name) {
		assertThrows(IllegalArgumentException.class, () ->{
			new Task("0123", name, "This is just some info");
		});
	}
	
	// Verify task description constraints
	@ParameterizedTest
	@ValueSource(strings = {"This is a very very very very very long description", ""})
	@NullSource
	void parameterizedDescriptionTest(String description) {
		assertThrows(IllegalArgumentException.class, () ->{
			new Task("0123", "Just A Task", description);
		});
	}

}

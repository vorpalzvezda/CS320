package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import appointment.Appointment;

public class AppointmentTest {
	
	private Appointment apt;
	// Instantiate a valid Appointment object to be used in subsequent tests
	@BeforeEach
	void setupValidAppointment() {
		apt = new Appointment("0123465789", LocalDate.now().plusDays(1), "This is a valid appointment description");
	}
	
	// Verify Appointment object attribute values 
	@Test
	void testValidAppointment(){
		assertEquals("0123465789", apt.getAptID());
		assertEquals(LocalDate.now().plusDays(1), apt.getAptDate());
		assertEquals("This is a valid appointment description", apt.getAptDescription());
	}
	
	// Verifies Appointment with invalid ID cannot be instantiated
	@ParameterizedTest
	@ValueSource(strings = {"", "01234567891"})
	@NullSource
	void testInvalidID(String id) {
		assertThrows(IllegalArgumentException.class, () ->{
			new Appointment(id, LocalDate.now().plusDays(1), "This is some info");
		});
	}
	
	// Verifies Appointment date in the past cannot be instantiated
	@Test
	void testInvalidDate() {
		assertThrows(IllegalArgumentException.class, () ->{
			apt.setDate(LocalDate.now().minusDays(1));
		});
	}
	
	// Verify Appointment date updates correctly
	@Test
	void testValidDate() {
		LocalDate futureDate = LocalDate.now().plusDays(7);
		apt.setDate(futureDate);
		assertEquals(futureDate, apt.getAptDate());
	}
	
	// Verifies Appointment with null date cannot be instantiated
	@Test
	void testNullDate() {
		assertThrows(IllegalArgumentException.class, () ->{
			apt.setDate(null);
		});
	}
	
	// Verifies Appointment description is properly constrained
	@ParameterizedTest
	@ValueSource(strings = {"", "This is a very very very very very too long description"})
	@NullSource
	void testInvalidDescription(String info) {
		assertThrows(IllegalArgumentException.class, () ->{
			apt.setDescription(info);
		});
	}
	
	// Verify Appointment description updates correctly
	@Test
	void testValidDescription() {
		String updateInfo = "Update my appointment description.";
		apt.setDescription(updateInfo);
		assertEquals(updateInfo, apt.getAptDescription());
	}
}

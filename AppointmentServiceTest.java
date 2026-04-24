package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appointment.Appointment;
import appointmentservice.AppointmentService;

public class AppointmentServiceTest {
	
	private AppointmentService service;
	
	// Starts a new service before running each test
	@BeforeEach
	void serviceSetup() {
		service = new AppointmentService();
	}
	
	// Verifies Service can add a new Appointment successfully (no duplicates)
	@Test
	void testAddAppointment() {
		service.addAppointment("validID", LocalDate.now().plusDays(7), "Appointment for next week");
		Appointment apt = service.getAppointment("validID");
		
		assertEquals("validID", apt.getAptID());
		assertEquals(LocalDate.now().plusDays(7), apt.getAptDate());
		assertEquals("Appointment for next week", apt.getAptDescription());
		
		// Verifies appointment with duplicate ID cannot be added
		assertThrows(IllegalArgumentException.class, () ->{
			service.addAppointment("validID", LocalDate.now().plusDays(7), "Appointment for next week");
		});
	}
	
	// Verifies Service can delete an Appointment (no further access)
	@Test
	void testDeleteAppointment() {
		service.addAppointment("validID", LocalDate.now().plusDays(7), "Appointment for next week");
		service.deleteAppointment("validID");
		
		// Verify service no longer contains deleted appointment
		assertThrows(IllegalArgumentException.class, () ->{
			service.getAppointment("validID");
		});
		
		// Verify an invalid ID cannot be deleted
		assertThrows(IllegalArgumentException.class, () ->{
			service.deleteAppointment("validID");
		});
		
	}
}

package appointmentservice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import appointment.Appointment;

/**
 * Manages Appointment objects in memory 
 * 
 * AppointmentService: adding, deleting, retrieving appointment objects
 * Appointment validation handles by Appointment Class definition
 */
public class AppointmentService{
	
	// Stores Appointment objects 
	private final Map<String, Appointment> aptService = new HashMap<>();
	
	/**
	 * Creates and stores a new Appointment Object
	 * @param id
	 * @param date
	 * @param info
	 */
	public void addAppointment(String id, LocalDate date, String info) {
		if (aptService.containsKey(id)) {
			throw new IllegalArgumentException("ID already exists");
		}
		
		Appointment apt = new Appointment(id, date, info);
		aptService.put(id, apt);
		
	}
	
	/**
	 * Deletes Appointments by unique ID
	 * @param id
	 */
	public void deleteAppointment(String id) {
		if(aptService.containsKey(id)) {
			aptService.remove(id);
		}else {
			throw new IllegalArgumentException("Invalid ID");
		}
	}
	
	/**
	 * Searches for an Appointment with a matching unique ID
	 * @param id
	 * @return Appointment object
	 */
	public Appointment getAppointment(String id) {
		if(!aptService.containsKey(id)) {
			throw new IllegalArgumentException("Invalid ID");
		}
		return aptService.get(id);
	}
}

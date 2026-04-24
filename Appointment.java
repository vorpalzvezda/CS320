package appointment;

import java.time.LocalDate;

/**
 * Defines the Appointment Class object with attribute validation
 * 
 * Constraints:
 * - aptID: required, maximum length 10, immutable
 * - aptdate: required, must before in the future
 * - aptdescription: required, maximum length 50
 */
public class Appointment {
	private final String aptID;
	private LocalDate aptdate;
	private String aptdescription;
	
	/**
	 * Constructs an Appointment object
	 * @param id
	 * @param date
	 * @param info
	 */
	public Appointment(String id, LocalDate date, String info){
		if (id == null || id.length() > 10 || id.length() == 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		this.aptID = id;
		setDate(date);
		setDescription(info);
	}
	
	/**
	 * Validates and sets the aptdate attribute 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		if (date == null || date.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Invalid Date");
		}
		
		this.aptdate = date;
	}
	
	/**
	 * Validates and sets the aptdescription attribute
	 * @param info
	 */
	public void setDescription(String info) {
		if (info == null || info.length() > 50 || info.length() == 0) {
			throw new IllegalArgumentException("Invalid Description");
		}
		
		this.aptdescription = info;
	}
	
	/*
	 * GETTER Methods: Return attribute values
	 */
	public String getAptID() {
		return this.aptID;
	}
	
	public LocalDate getAptDate() {
		return this.aptdate;
	}
	
	public String getAptDescription() {
		return this.aptdescription;
	}
}

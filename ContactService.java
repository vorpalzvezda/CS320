package contactservice;

import java.util.HashMap;
import java.util.Map;

import contact.Contact;

/**
 * Manages Contact objects in memory
 * 
 * ContactService: adding, updating, retrieving, and deleting contacts by unique contactID
 * Field validation is enforced by the Contact Class definition
 */
public class ContactService {
	
	// Stores contact objects
	private final Map<String, Contact> contactList = new HashMap<>();
	
	/**
	 * Creates and stores new Contact objects
	 * 
	 * @param contactID
	 * @param firstName
	 * @param lastName
	 * @param number
	 * @param address
	 * @throws IllegalArgumentException if the ID already exists or input is invalid
	 */
	public void addContact(
			String contactID, 
			String firstName, 
			String lastName, 
			String number,
			String address){
		
		if(contactList.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID already exists");
		}
		
		Contact contact = new Contact(contactID, firstName, lastName, number, address);
		contactList.put(contactID, contact);
	}
	
	/**
	 * Retrieves a contact by ID after validation
	 * 
	 * This helper method consolidates lookup behavior 
	 * 
	 * @param contactID
	 * @return contact class object
	 * @throws IllegalArgumentException if the ID is null or not found
	 */
	private Contact findContact(String contactID) {
		
		Contact contact = contactList.get(contactID);
		
		if (contact == null) {
			throw new IllegalArgumentException("Contact ID Not Found");
		}
		
		return contact;
		
	}
	
	/**
	 * Returns a Contact by given ID
	 * 
	 * @param contactID
	 * @return contact
	 */
	public Contact getContact(String contactID) {
		return findContact(contactID);
	}
	
	/**
	 * Removes a Contact from in-memory storage 
	 * 
	 * @param contactID
	 */
	public void deleteContact(String contactID) {
		findContact(contactID);
		contactList.remove(contactID);
	}
	
	/**
	 * Updates a Contact's first name 
	 * 
	 * @param contactID
	 * @param firstName (new name)
	 */
	public void updateFirstName(String contactID, String firstName) {
		findContact(contactID).setFirstName(firstName);
	}
	
	/**
	 * Updates a Contact's last name
	 * 
	 * @param contactID
	 * @param lastName (new name)
	 */
	public void updateLastName(String contactID, String lastName) {
		findContact(contactID).setLastName(lastName);
	}
	
	/**
	 * Updates a Contact's phone number
	 * 
	 * @param contactID
	 * @param number (new number)
	 */
	public void updateNumber(String contactID, String number) {
		findContact(contactID).setNumber(number);
	}
	
	/**
	 * Updates a Contact's address
	 * 
	 * @param contactID
	 * @param address (new address)
	 */
	public void updateAddress(String contactID, String address) {
		findContact(contactID).setAddress(address);
	}
}

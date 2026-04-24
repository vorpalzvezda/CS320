package tests;

import org.junit.jupiter.api.Test;

import contact.Contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import contactservice.ContactService;

public class ContactServiceTest {
	
	private ContactService service;
	
	// Initialize ContactService to be used by subsequent tests
	@BeforeEach
	void serviceSetup() {
		service = new ContactService();
	}
	
	// Verify a valid contact can be added and then retrieved by ID
	@Test
	void testAddContact(){
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		
		Contact contact = service.getContact("569");
		assertEquals("569", contact.getContactId());
	}
	
	// Verify duplicate IDs cannot be added
	@Test
	void testAddDuplicateContactID() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		
		assertThrows(IllegalArgumentException.class, () ->{
			service.addContact("569", "Alex", "Wienecke", "0987654321", "123 Oak Avenue");
		});
	}
	
	// Verify contacts with invalid names cannot be added
	@Test
	void testAddInvalidContactFirstName() {
		assertThrows(IllegalArgumentException.class, () ->{
			service.addContact("555", "Dylan678910", "Oh no", "1234567980", "1600 Penn Ave");
		});
	}
	
	// Verify deletion of stored contact is successful
	@Test
	void testDeleteContact() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		service.deleteContact("569");
		
		assertThrows(IllegalArgumentException.class, () ->{
			service.getContact("569");
		});
	}
	
	// Verify invalid contacts cannot be deleted 
	@Test
	void testDeleteInvalidContact() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		service.deleteContact("569");
		
		assertThrows(IllegalArgumentException.class, () ->{
			service.deleteContact("569");
		});
	}
	
	// Verify first name updates successfully 
	@Test
	void testUpdateFirstName() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		service.updateFirstName("569", "Brandon");
		
		assertEquals("Brandon", service.getContact("569").getFirstName());
	}
	 // Verify last name updates successfully 
	@Test
	void testUpdateLastName() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		service.updateLastName("569", "Targaryen");
		
		assertEquals("Targaryen", service.getContact("569").getLastName());
	}
	
	// Verify phone number updates successfully 
	@Test
	void testUpdateNumber() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		service.updateNumber("569", "4561237891");
		
		assertEquals("4561237891", service.getContact("569").getNumber());
	}
	
	// Verify address updates successfully 
	@Test
	void testUpdateAddress() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		service.updateAddress("569", "321 elpam Enal");
		
		assertEquals("321 elpam Enal", service.getContact("569").getAddress());
	}
	
	// Verify invalid contacts cannot be updated
	@Test
	void testUpdateInvalidID() {
		assertThrows(IllegalArgumentException.class, () ->{
			service.updateLastName("569", "Pohtaytoes");
		});
	}
	
	// Verify contacts cannot be updated with invalid input 
	// & ensure original value is intact
	@Test
	void testUpdateAddressInvalid() {
		service.addContact("569", "Dylan", "Wienecke", "1234567890", "123 Maple Lane");
		String address = "a".repeat(31); // Over the 30 character limit
		
		assertThrows(IllegalArgumentException.class, () ->{
			service.updateAddress("569", address);
		});
		
		assertEquals("123 Maple Lane", service.getContact("569").getAddress());
	}

}

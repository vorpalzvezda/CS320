package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import contact.Contact;

public class ContactTest {
	
	private Contact contact;
	
	// Verify contacts are initialized successfully with valid input
	@BeforeEach
	void setupValidContact() {
		contact = new Contact(
				"123456", 
				"Dylan",
				"Wienecke",
				"1234567890",
				"123 Maple Drive");
	}
	
	@Test
	void testValidContactSetup() {
		assertEquals("123456", contact.getContactId());
		assertEquals("Dylan", contact.getFirstName());
		assertEquals("Wienecke", contact.getLastName());
		assertEquals("1234567890", contact.getNumber());
		assertEquals("123 Maple Drive", contact.getAddress());
	}
	
	// Verify Contact object cannot be instantiated with an invalid ID
	@ParameterizedTest
	@ValueSource(strings = {"", "01234567891"})
	@NullSource
	void testContactInvalidID(String id) {
		assertThrows(IllegalArgumentException.class, () ->{
			new Contact(id, "Dylan", "Wienecke", "1234567890", "123 Maple Drive");
		});
	}
	
	// Verify firstName cannot be set to an invalid input
	@ParameterizedTest
	@ValueSource(strings = {"", "01234567891"})
	@NullSource
	void testFirstNameInvalid(String firstName) {
		assertThrows(IllegalArgumentException.class, () ->{
			contact.setFirstName(firstName);
		});
	}

	
	// Verify lastName cannot be set to an invalid input
	@ParameterizedTest
	@ValueSource(strings = {"", "01234567891"})
	@NullSource
	void testLastNameInvalid(String lastName) {
		assertThrows(IllegalArgumentException.class, () ->{
			contact.setLastName(lastName);
		});
	}
	
	// Verify Number cannot be set to an invalid input
	@ParameterizedTest
	@ValueSource(strings = {"0123abcdef", "01234567891", "123"})
	@NullSource
	void testNumberInvalid(String number) {
		assertThrows(IllegalArgumentException.class, () ->{
			contact.setNumber(number);
		});
	}
	
	// Verify Address cannot be set to an invalid input
	@ParameterizedTest
	@ValueSource(strings ={"", "This address is entirely too long"})
	@NullSource
	void testAddressInvalid(String address) {
		assertThrows(IllegalArgumentException.class, () ->{
			contact.setAddress(address);
		});
	}
}
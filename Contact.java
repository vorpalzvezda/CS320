package contact;

/**
 * Defines the Contact Class object with attribute validation
 * 
 * Constraints:
 * - contactID: required, maximum length 10, immutable 
 * - firstName: required, maximum length 10
 * - lastName: required, maximum length 10
 * - number: required, exactly 10 digits
 * - address: required, maximum length 30
 * 
 * Attributed are validated during construction and in setter methods
 */
public class Contact {
	
	// Attribute Fields 
	private final String contactId;
	private String firstName;
	private String lastName;
	private String number;
	private String address;
	
	/**
	 * Constructs a Contact object 
	 * Uses setter methods to enforce validation rules
	 * 
	 * @param contactID
	 * @param firstName
	 * @param lastName
	 * @param Number
	 * @param Address
	 */
	public Contact(
			String contactID,
			String firstName,
			String lastName,
			String Number,
			String Address) {
		
		if (contactID == null || contactID.length() > 10 || contactID.length() == 0) {
			throw new IllegalArgumentException("Invalid Contact ID");
		}
		this.contactId = contactID;
		setFirstName(firstName);
		setLastName(lastName);
		setNumber(Number);
		setAddress(Address);
		
	}
	
	/*
	 * GETTER Methods: Return attribute value
	 */
	public String getContactId() {
		return contactId;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	
	/**
	 * Updates first name
	 * @param name
	 */
	public void setFirstName(String name) {
		if (name == null || name.length() > 10 || name.length() == 0) {
			throw new IllegalArgumentException("Invalid First Name");
		}
		
		this.firstName = name;
	}
	
	/**
	 * Updates last name
	 * @param name
	 */
	public void setLastName(String name) {
		if (name == null || name.length() > 10 || name.length() == 0) {
			throw new IllegalArgumentException("Invalid Last Name");
		}
		this.lastName = name;
	}
	
	/**
	 * Updates phone number 
	 * @param number
	 */
	public void setNumber(String number) {
		if (number == null || number.length() != 10) {
			throw new IllegalArgumentException("Invalid Phone Number");
		}
			
		for (int i = 0; i < number.length(); i++) {
			if(!Character.isDigit(number.charAt(i))) {
				throw new IllegalArgumentException("Invalid Phone Number");
			}
		}
		
		this.number = number;
	}
	
	/**
	 * Updates address
	 * @param address
	 */
	public void setAddress(String address) {
		if (address == null || address.length() > 30 || address.length() == 0) {
			throw new IllegalArgumentException("Invalid Address");
		}
		
		this.address = address;
	}
}

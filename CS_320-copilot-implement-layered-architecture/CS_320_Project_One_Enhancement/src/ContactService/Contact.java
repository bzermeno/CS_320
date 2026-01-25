/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: Contact class allows user to create a Contact object with a unique contactID,
 *  with first name, last name, phone number and address fields. The fields must meet the given
 *  constraints.
 *  Updated: January 20, 2026
 */
package ContactService;

public class Contact {
	private final String contactID;	 // contactID is immutable
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	// Initialize length constraints
	private final int MAX_CONTACTID_LENGTH = 10;
	private final int MAX_FIRSTNAME_LENGTH = 10;
	private final int MAX_LASTNAME_LENGTH = 10;
	private final int MAX_ADDRESS_LENGTH = 30;
	
	// Constructor with validation
	public Contact(String contactID, String firstName, String lastName, String phone, String address) {
		if (contactID == null) {
			throw new NullPointerException("contactID cannont be null");
		}
		if (contactID.length() > MAX_CONTACTID_LENGTH) {
			throw new IllegalArgumentException("contactID length cannot exceed 10 characters");
		}
		if (firstName == null) {
			throw new NullPointerException("firstName cannont be null");
		}
		if (firstName.length() > MAX_FIRSTNAME_LENGTH) {
			throw new IllegalArgumentException("firstname length cannot exceed 10 characters");
		}
		if (lastName == null) {
			throw new NullPointerException("lastName cannont be null");
		}
		if (lastName.length() > MAX_LASTNAME_LENGTH) {
			throw new IllegalArgumentException("lastName length cannot exceed 10 characters");
		}
		if (phone == null) {
			throw new NullPointerException("phone cannont be null");
		}
		if (!phone.matches("\\d{10}")) {
			throw new IllegalArgumentException("phone must be exactly 10 digits");
		}
		if (address == null) {
			throw new NullPointerException("address cannont be null");
		}
		if (address.length() > MAX_ADDRESS_LENGTH) {
			throw new IllegalArgumentException("address length cannot exceed 30 characters");
		}
		this.contactID = contactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}

	// Getters
    public String getContactID() {
        return contactID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters with validation
    public void setFirstName(String firstName) {
    	if (firstName == null) {
			throw new NullPointerException("firstName cannont be null");
		}
		if (firstName.length() > MAX_FIRSTNAME_LENGTH) {
			throw new IllegalArgumentException("firstname length cannot exceed 10 characters");
		}
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
    	if (lastName == null) {
			throw new NullPointerException("lastName cannont be null");
		}
		if (lastName.length() > MAX_LASTNAME_LENGTH) {
			throw new IllegalArgumentException("lastName length cannot exceed 10 characters");
		}
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
    	if (phone == null) {
			throw new NullPointerException("phone cannont be null");
		}
		if (!phone.matches("\\d{10}")) {
			throw new IllegalArgumentException("phone must be exactly 10 digits");
		}
        this.phone = phone;
    }

    public void setAddress(String address) {
    	if (address == null) {
			throw new NullPointerException("address cannont be null");
		}
		if (address.length() > MAX_ADDRESS_LENGTH) {
			throw new IllegalArgumentException("address length cannot exceed 30 characters");
		}
        this.address = address;
    }
}

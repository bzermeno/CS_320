/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: Contact class allows user to create a Contact object with a unique contactID,
 *  with first name, last name, phone number and address fields. The fields must meet the given
 *  constraints. 
 */
package ContactService;

public class Contact {
	private final String contactID;	 // contactID is immutable
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	// Constructor with validation
	public Contact(String contactID, String firstName, String lastName, String phone, String address) {
		if (contactID == null || contactID.length() > 10) {
			throw new IllegalArgumentException("Invalid ID");
		}
		if (firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("Invalid first name");
		}
		if (lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Invalid last name");
		}
		if (phone == null || phone.length() != 10) {
			throw new IllegalArgumentException("Invalid phone number");
		}
		if (address == null || address.length() > 30) {
			throw new IllegalArgumentException("Invalid address");
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
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }
}

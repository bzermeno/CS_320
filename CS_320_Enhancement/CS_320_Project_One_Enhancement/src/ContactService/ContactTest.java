/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: ContactTest class tests that a Contact object with valid fields is created successfully,
 *  and that attempting to create an object with invalid fields throws the proper exceptions.
 *  Updated: January 20, 2026
 */
package ContactService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactTest {
	private Contact contact;
	
	@BeforeEach
	void newContact() {
		// Create Contact object with valid fields
		contact = new Contact("100", "John", "Smith", "1234567890", "12345 Main St");
	}
	
	@Test
	void testValidContact() {
		assertEquals("100", contact.getContactID());
		assertEquals("John", contact.getFirstName());
		assertEquals("Smith", contact.getLastName());
		assertEquals("1234567890", contact.getPhone());
		assertEquals("12345 Main St", contact.getAddress());
	}

	@Test
	void testInvalidContactId() {
		// contactID is null
		assertThrows(NullPointerException.class, () -> {
			new Contact(null, "John", "Smith", "1234567890", "12345 Main St");
        });
		// contactID is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345678901", "John", "Smith", "1234567890", "12345 Main St");
        });
	}
	
	@Test
	void testInvalidFirstName() {
		// firstName is null
		assertThrows(NullPointerException.class, () -> {
			new Contact("100", null, "Smith", "1234567890", "12345 Main St");
        });
		// firstName is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("100", "John11111111", "Smith", "1234567890", "12345 Main St");
        });
	}
	
	@Test
	void testInvalidLastName() {
		// lastName is null
		assertThrows(NullPointerException.class, () -> {
			new Contact("100", "John", null, "1234567890", "12345 Main St");
        });
		// lastName is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("100", "John", "Smith11111111", "1234567890", "12345 Main St");
        });
	}
	
	@Test
	void testInvalidPhone() {
		// phone is null
		assertThrows(NullPointerException.class, () -> {
			new Contact("100", "John", "Smith", null, "12345 Main St");
        });
		// phone is too short
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("100", "John", "Smith", "12345", "12345 Main St");
        });
		// phone is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("100", "John", "Smith", "123456789012", "12345 Main St");
        });
	}
	
	@Test
	void testInvalidAddress() {
		// address is null
		assertThrows(NullPointerException.class, () -> {
			new Contact("100", "John", "Smith", "1234567890", null);
        });
		// address is too long
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact("100", "John", "Smith", "1234567890", "12345 Main St 12345 Main St 12345 Main St");
        });
	}
	
	@Test
	void testSetFirstName() {
		// Assign firstName with new valid attribute
		contact.setFirstName("200");
		assertEquals("200", contact.getFirstName());
		// firstName is null
		assertThrows(NullPointerException.class, () -> {
			contact.setFirstName(null);
        });
		// firstName is too long
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setFirstName("John11111111");
        });
	}
	
	@Test
	void testSetLastName() {
		// Assign lastName with new valid attribute
		contact.setLastName("Jones");
		assertEquals("Jones", contact.getLastName());
		// lastName is null
		assertThrows(NullPointerException.class, () -> {
			contact.setLastName(null);
        });
		// lastName is too long
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setLastName("Smith11111111");
        });
	}
	
	@Test
	void testSetPhone() {
		// Assign phone with new valid attribute
		contact.setPhone("1112223333");
		assertEquals("1112223333", contact.getPhone());
		// phone is null
		assertThrows(NullPointerException.class, () -> {
			contact.setPhone(null);
        });
		// phone is too short
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setPhone("123");
        });
		// phone is too long
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setPhone("123456789012");
        });
	}
	
	@Test
	void testSetAddress() {
		// Assign address with new valid attribute
		contact.setAddress("One Tree Hill");
		assertEquals("One Tree Hill", contact.getAddress());
		// address is null
		assertThrows(NullPointerException.class, () -> {
			contact.setAddress(null);
        });
		// address is too long
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setAddress("One Tree Hill One Tree Hill One Tree Hill");
        });
	}
}


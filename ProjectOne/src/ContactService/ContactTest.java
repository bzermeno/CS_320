/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: ContactTest class tests that a Contact object with valid fields is created successfully,
 *  and that attempting to create an object with invalid fields throws the proper exceptions.
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
		assertEquals(contact.getContactID(), "100");
		assertEquals(contact.getFirstName(), "John");
		assertEquals(contact.getLastName(), "Smith");
		assertEquals(contact.getPhone(), "1234567890");
		assertEquals(contact.getAddress(), "12345 Main St");
	}

	@Test
	void testInvalidContactId() {
		// contactID is null
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertEquals(contact.getFirstName(), "200");
		// firstName is null
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertEquals(contact.getLastName(), "Jones");
		// lastName is null
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertEquals(contact.getPhone(), "1112223333");
		// phone is null
		assertThrows(IllegalArgumentException.class, () -> {
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
		assertEquals(contact.getAddress(), "One Tree Hill");
		// address is null
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setAddress(null);
        });
		// address is too long
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setAddress("One Tree Hill One Tree Hill One Tree Hill");
        });
	}
}


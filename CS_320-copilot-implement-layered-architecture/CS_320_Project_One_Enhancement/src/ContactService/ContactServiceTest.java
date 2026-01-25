/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: ContactServiceTest class tests that contacts are successfully added to the HashMap
 *  contacts, contacts are deleted successfully, and that attempts to add invalid contacts throw
 *  the proper exceptions.
 */
package ContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService contactService;
    private Contact contact;

    @BeforeEach
    public void newContactService() {
        contactService = new ContactService();
        contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
    }

    @Test
    public void testAddContactSuccessfully() {
        // Add a new Contact object with valid fields to contacts
    	Contact newContact = new Contact("54321", "Jane", "Smith", "0987654321", "456 Elm St");
        contactService.addContact(newContact);
        assertEquals("Jane", newContact.getFirstName());
    }

    @Test
    public void testAddContactWithDuplicateId() {
    	// contactID already exists
        assertThrows(IllegalArgumentException.class, () -> {
            Contact duplicate = new Contact("12345", "Mike", "Brown", "1231231234", "789 Oak St");
            contactService.addContact(duplicate);
        });
    }

    @Test
    public void testDeleteContactSuccessfully() {
    	// contact exists
        contactService.deleteContact("12345");
        // contact does not exist
        assertThrows(IllegalArgumentException.class, () -> contactService.deleteContact("12345"));
    }

    @Test
    public void testUpdateFirstName() {
        contactService.updateFirstName("12345", "Alice");
        assertEquals("Alice", contact.getFirstName());
    }

    @Test
    public void testUpdateLastName() {
        contactService.updateLastName("12345", "White");
        assertEquals("White", contact.getLastName());
    }

    @Test
    public void testUpdatePhone() {
        contactService.updatePhone("12345", "1112223333");
        assertEquals("1112223333", contact.getPhone());
    }

    @Test
    public void testUpdatePhoneWithInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("12345", "1234");
        });
    }

    @Test
    public void testUpdateAddress() {
        contactService.updateAddress("12345", "999 Maple St");
        assertEquals("999 Maple St", contact.getAddress());
    }
    
    @Test
    public void testCreateContactWithInvalidFields() {
    	// contactID is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
        // contactID is too long
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
        // firstName is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", null, "Doe", "1234567890", "123 Main St");
        });
        // phone is invalid
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "abc", "123 Main St");
        });
        // address is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "1234567890", null);
        });
    }
}

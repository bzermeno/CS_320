/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: ContactService class allows user to add Contact objects to the HashMap contacts, delete
 *  a contact identified by contactID, and edit contact fields, except for contactID, which is immutable.
 */
package ContactService;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
	// HashMap to hold Contact objects
    private final Map<String, Contact> contacts = new HashMap<>();
    
    public void addContact(Contact contact) {
        
    	if (contact == null) {
            throw new IllegalArgumentException("Contact must be not be null.");
        }
        if (contacts.containsKey(contact.getContactID())) {
        	throw new IllegalArgumentException("Contact must have a unique ID.");
        }
        contacts.put(contact.getContactID(), contact);
    }

    public void deleteContact(String contactID) {
        // contactID does not exist
    	if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contacts.remove(contactID);
    }

    // Functions allow user to assign new values to contact fields.
    public void updateFirstName(String contactID, String newFirstName) {
        Contact contact = getContact(contactID);
        contact.setFirstName(newFirstName);
    }

    public void updateLastName(String contactID, String newLastName) {
        Contact contact = getContact(contactID);
        contact.setLastName(newLastName);
    }

    public void updatePhone(String contactID, String newPhone) {
        Contact contact = getContact(contactID);
        contact.setPhone(newPhone);
    }

    public void updateAddress(String contactID, String newAddress) {
        Contact contact = getContact(contactID);
        contact.setAddress(newAddress);
    }
    
    private Contact getContact(String contactID) {
        Contact contact = contacts.get(contactID);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        return contact;
    }
}
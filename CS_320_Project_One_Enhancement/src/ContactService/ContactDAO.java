/*  Author Name: Beau Zermeno
 *  Date: January 24, 2026 
 *  Course ID: CS-320
 *  Description: ContactDAO (Data Access Object) class handles all data persistence operations
 *  for Contact objects. This layer encapsulates the data storage implementation (HashMap)
 *  and provides CRUD operations, following the separation of concerns principle.
 *  
 *  Purpose: Isolates data access logic from business logic, making the system more maintainable
 *  and allowing for easier changes to the underlying data storage mechanism without affecting
 *  other layers.
 */
package ContactService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Data Access Object for Contact entities.
 * Handles all database/storage operations for contacts.
 */
public class ContactDAO {
    // HashMap to hold Contact objects (in-memory storage)
    private final Map<String, Contact> contacts = new HashMap<>();
    
    /**
     * Saves a new contact to the data store.
     * 
     * @param contact The Contact object to save
     * @throws IllegalArgumentException if contact is null or ID already exists
     */
    public void save(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact must have a unique ID.");
        }
        contacts.put(contact.getContactID(), contact);
    }
    
    /**
     * Updates an existing contact in the data store.
     * 
     * @param contact The Contact object with updated information
     * @throws IllegalArgumentException if contact is null or doesn't exist
     */
    public void update(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }
        if (!contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contacts.put(contact.getContactID(), contact);
    }
    
    /**
     * Deletes a contact from the data store by ID.
     * 
     * @param contactID The unique identifier of the contact to delete
     * @throws IllegalArgumentException if contactID doesn't exist
     */
    public void delete(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contacts.remove(contactID);
    }
    
    /**
     * Finds a contact by its unique ID.
     * 
     * @param contactID The unique identifier of the contact
     * @return Optional containing the Contact if found, empty Optional otherwise
     */
    public Optional<Contact> findById(String contactID) {
        return Optional.ofNullable(contacts.get(contactID));
    }
    
    /**
     * Checks if a contact with the given ID exists.
     * 
     * @param contactID The unique identifier to check
     * @return true if contact exists, false otherwise
     */
    public boolean exists(String contactID) {
        return contacts.containsKey(contactID);
    }
}

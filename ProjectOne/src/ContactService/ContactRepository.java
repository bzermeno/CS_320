/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: ContactRepository class handles data access operations for Contact objects.
 *  This is the data access layer in the layered architecture.
 */
package ContactService;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    // HashMap to hold Contact objects
    private final Map<String, Contact> contacts = new HashMap<>();
    
    /**
     * Adds a contact to the repository
     * @param contact The contact to add
     */
    public void save(Contact contact) {
        contacts.put(contact.getContactID(), contact);
    }
    
    /**
     * Finds a contact by ID
     * @param contactID The ID of the contact to find
     * @return The contact if found, null otherwise
     */
    public Contact findById(String contactID) {
        return contacts.get(contactID);
    }
    
    /**
     * Checks if a contact exists by ID
     * @param contactID The ID to check
     * @return true if contact exists, false otherwise
     */
    public boolean existsById(String contactID) {
        return contacts.containsKey(contactID);
    }
    
    /**
     * Deletes a contact by ID
     * @param contactID The ID of the contact to delete
     */
    public void deleteById(String contactID) {
        contacts.remove(contactID);
    }
    
    /**
     * Returns all contacts
     * @return Collection of all contacts
     */
    public Collection<Contact> findAll() {
        return new ArrayList<>(contacts.values());
    }
}

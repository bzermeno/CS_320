/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: ContactService class contains business logic for managing Contact objects.
 *  This service layer delegates data persistence operations to ContactDAO, following the
 *  layered architecture pattern. It focuses on business rules, validation, and coordination
 *  between the Controller and DAO layers.
 *  
 *  Purpose: Implements core business logic and rules for contact management. This layer ensures
 *  consistent application logic and provides an abstraction between the Controller and DAO layers.
 *  Updated: January 24, 2026
 */
package ContactService;

/**
 * Service layer for Contact business logic.
 * Coordinates between Controller and DAO layers, focusing on business rules.
 */
public class ContactService {
    // DAO instance for data access operations
    private final ContactDAO contactDAO;
    
    /**
     * Constructor with dependency injection for DAO layer.
     * 
     * @param contactDAO The data access object for contact operations
     */
    public ContactService(ContactDAO contactDAO) {
        if (contactDAO == null) {
            throw new IllegalArgumentException("ContactDAO must not be null.");
        }
        this.contactDAO = contactDAO;
    }
    
    /**
     * Default constructor that creates its own DAO instance.
     * Useful for backward compatibility with existing tests.
     */
    public ContactService() {
        this.contactDAO = new ContactDAO();
    }
    
    /**
     * Adds a new contact to the system.
     * Business logic: Validates contact and ensures unique ID before saving.
     * 
     * @param contact The Contact object to add
     * @throws IllegalArgumentException if contact is invalid or ID exists
     */
    public void addContact(Contact contact) {
        // Business rule: Contact must not be null
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }
        // Delegate to DAO layer for persistence
        contactDAO.save(contact);
    }

    /**
     * Deletes a contact from the system.
     * Business logic: Verifies contact exists before deletion.
     * 
     * @param contactID The unique identifier of the contact to delete
     * @throws IllegalArgumentException if contactID doesn't exist
     */
    public void deleteContact(String contactID) {
        // Delegate to DAO layer for deletion
        contactDAO.delete(contactID);
    }

    /**
     * Updates the first name of a contact.
     * Business logic: Retrieves contact, applies change, and persists update.
     * 
     * @param contactID The unique identifier of the contact
     * @param newFirstName The new first name value
     * @throws IllegalArgumentException if contactID doesn't exist or validation fails
     */
    public void updateFirstName(String contactID, String newFirstName) {
        Contact contact = getContact(contactID);
        contact.setFirstName(newFirstName);
        contactDAO.update(contact);
    }

    /**
     * Updates the last name of a contact.
     * Business logic: Retrieves contact, applies change, and persists update.
     * 
     * @param contactID The unique identifier of the contact
     * @param newLastName The new last name value
     * @throws IllegalArgumentException if contactID doesn't exist or validation fails
     */
    public void updateLastName(String contactID, String newLastName) {
        Contact contact = getContact(contactID);
        contact.setLastName(newLastName);
        contactDAO.update(contact);
    }

    /**
     * Updates the phone number of a contact.
     * Business logic: Retrieves contact, applies change, and persists update.
     * 
     * @param contactID The unique identifier of the contact
     * @param newPhone The new phone number value
     * @throws IllegalArgumentException if contactID doesn't exist or validation fails
     */
    public void updatePhone(String contactID, String newPhone) {
        Contact contact = getContact(contactID);
        contact.setPhone(newPhone);
        contactDAO.update(contact);
    }

    /**
     * Updates the address of a contact.
     * Business logic: Retrieves contact, applies change, and persists update.
     * 
     * @param contactID The unique identifier of the contact
     * @param newAddress The new address value
     * @throws IllegalArgumentException if contactID doesn't exist or validation fails
     */
    public void updateAddress(String contactID, String newAddress) {
        Contact contact = getContact(contactID);
        contact.setAddress(newAddress);
        contactDAO.update(contact);
    }
    
    /**
     * Helper method to retrieve a contact by ID.
     * Business logic: Ensures contact exists before returning.
     * 
     * @param contactID The unique identifier of the contact
     * @return The Contact object
     * @throws IllegalArgumentException if contactID doesn't exist
     */
    private Contact getContact(String contactID) {
        return contactDAO.findById(contactID)
            .orElseThrow(() -> new IllegalArgumentException("Contact ID not found"));
    }
}
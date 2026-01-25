/*  Author Name: Beau Zermeno
 *  Date: January 24, 2026 
 *  Course ID: CS-320
 *  Description: ContactController handles incoming requests/user interactions for contact management.
 *  This controller layer is responsible for input validation, request handling, and delegating
 *  business logic to the ContactService layer. It follows the Model-View-Controller (MVC) pattern.
 *  
 *  Purpose: Provides a clear entry point for contact operations, handling user input/output and
 *  delegating to the service layer. This separation allows for different interfaces (CLI, GUI, REST API)
 *  to be easily implemented without changing business logic.
 */
package ContactService;

/**
 * Controller layer for Contact operations.
 * Handles user input validation and delegates to service layer.
 */
public class ContactController {
    private final ContactService contactService;
    
    /**
     * Constructor with dependency injection for service layer.
     * 
     * @param contactService The service layer instance for business logic
     */
    public ContactController(ContactService contactService) {
        if (contactService == null) {
            throw new IllegalArgumentException("ContactService must not be null.");
        }
        this.contactService = contactService;
    }
    
    /**
     * Default constructor that creates its own service instance.
     */
    public ContactController() {
        this.contactService = new ContactService();
    }
    
    /**
     * Handles request to create a new contact.
     * Validates inputs and delegates to service layer.
     * 
     * @param contactID Unique identifier for the contact
     * @param firstName First name of the contact
     * @param lastName Last name of the contact
     * @param phone Phone number of the contact
     * @param address Address of the contact
     * @return Success message or error details
     */
    public String createContact(String contactID, String firstName, String lastName, 
                                 String phone, String address) {
        try {
            // Create contact with validation happening in the Contact constructor
            Contact contact = new Contact(contactID, firstName, lastName, phone, address);
            
            // Delegate to service layer
            contactService.addContact(contact);
            
            return "Contact created successfully with ID: " + contactID;
        } catch (Exception e) {
            return "Error creating contact: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to delete a contact.
     * 
     * @param contactID The unique identifier of the contact to delete
     * @return Success message or error details
     */
    public String deleteContact(String contactID) {
        try {
            // Input validation
            if (contactID == null || contactID.isEmpty()) {
                return "Error: Contact ID cannot be null or empty";
            }
            
            // Delegate to service layer
            contactService.deleteContact(contactID);
            
            return "Contact deleted successfully: " + contactID;
        } catch (Exception e) {
            return "Error deleting contact: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to update a contact's first name.
     * 
     * @param contactID The unique identifier of the contact
     * @param newFirstName The new first name value
     * @return Success message or error details
     */
    public String updateContactFirstName(String contactID, String newFirstName) {
        try {
            // Input validation
            if (contactID == null || contactID.isEmpty()) {
                return "Error: Contact ID cannot be null or empty";
            }
            
            // Delegate to service layer
            contactService.updateFirstName(contactID, newFirstName);
            
            return "Contact first name updated successfully";
        } catch (Exception e) {
            return "Error updating first name: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to update a contact's last name.
     * 
     * @param contactID The unique identifier of the contact
     * @param newLastName The new last name value
     * @return Success message or error details
     */
    public String updateContactLastName(String contactID, String newLastName) {
        try {
            // Input validation
            if (contactID == null || contactID.isEmpty()) {
                return "Error: Contact ID cannot be null or empty";
            }
            
            // Delegate to service layer
            contactService.updateLastName(contactID, newLastName);
            
            return "Contact last name updated successfully";
        } catch (Exception e) {
            return "Error updating last name: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to update a contact's phone number.
     * 
     * @param contactID The unique identifier of the contact
     * @param newPhone The new phone number value
     * @return Success message or error details
     */
    public String updateContactPhone(String contactID, String newPhone) {
        try {
            // Input validation
            if (contactID == null || contactID.isEmpty()) {
                return "Error: Contact ID cannot be null or empty";
            }
            
            // Delegate to service layer
            contactService.updatePhone(contactID, newPhone);
            
            return "Contact phone updated successfully";
        } catch (Exception e) {
            return "Error updating phone: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to update a contact's address.
     * 
     * @param contactID The unique identifier of the contact
     * @param newAddress The new address value
     * @return Success message or error details
     */
    public String updateContactAddress(String contactID, String newAddress) {
        try {
            // Input validation
            if (contactID == null || contactID.isEmpty()) {
                return "Error: Contact ID cannot be null or empty";
            }
            
            // Delegate to service layer
            contactService.updateAddress(contactID, newAddress);
            
            return "Contact address updated successfully";
        } catch (Exception e) {
            return "Error updating address: " + e.getMessage();
        }
    }
}

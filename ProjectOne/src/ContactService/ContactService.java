/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: ContactService class allows user to add Contact objects, delete
 *  a contact identified by contactID, and edit contact fields, except for contactID, which is immutable.
 *  This is the service layer in the layered architecture that uses ContactRepository for data access.
 */
package ContactService;

import java.util.Collection;

public class ContactService {
    // Repository for data access
    private final ContactRepository contactRepository;
    
    public ContactService() {
        this.contactRepository = new ContactRepository();
    }
    
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    public void addContact(Contact contact) {
        
    	if (contact == null) {
            throw new IllegalArgumentException("Contact must be not be null.");
        }
        if (contactRepository.existsById(contact.getContactID())) {
        	throw new IllegalArgumentException("Contact must have a unique ID.");
        }
        contactRepository.save(contact);
    }

    public void deleteContact(String contactID) {
        // contactID does not exist
    	if (!contactRepository.existsById(contactID)) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contactRepository.deleteById(contactID);
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
    
    public Contact getContact(String contactID) {
        Contact contact = contactRepository.findById(contactID);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        return contact;
    }
    
    public Collection<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
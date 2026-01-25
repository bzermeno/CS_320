/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: REST Controller for Contact management with CRUD operations
 */
package api.controller;

import ContactService.Contact;
import ContactService.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    
    private final ContactService contactService;
    
    public ContactController() {
        this.contactService = new ContactService();
    }
    
    /**
     * GET /api/contacts - Get all contacts
     */
    @GetMapping
    public ResponseEntity<Collection<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }
    
    /**
     * GET /api/contacts/{id} - Get a specific contact by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        try {
            Contact contact = contactService.getContact(id);
            return ResponseEntity.ok(contact);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * POST /api/contacts - Create a new contact
     */
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactRequest request) {
        try {
            Contact contact = new Contact(
                request.getContactID(),
                request.getFirstName(),
                request.getLastName(),
                request.getPhone(),
                request.getAddress()
            );
            contactService.addContact(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(contact);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * PUT /api/contacts/{id} - Update an existing contact
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable String id,
            @RequestBody ContactUpdateRequest request) {
        try {
            if (request.getFirstName() != null) {
                contactService.updateFirstName(id, request.getFirstName());
            }
            if (request.getLastName() != null) {
                contactService.updateLastName(id, request.getLastName());
            }
            if (request.getPhone() != null) {
                contactService.updatePhone(id, request.getPhone());
            }
            if (request.getAddress() != null) {
                contactService.updateAddress(id, request.getAddress());
            }
            Contact updatedContact = contactService.getContact(id);
            return ResponseEntity.ok(updatedContact);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE /api/contacts/{id} - Delete a contact
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        try {
            contactService.deleteContact(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Inner classes for request DTOs
    public static class ContactRequest {
        private String contactID;
        private String firstName;
        private String lastName;
        private String phone;
        private String address;
        
        public String getContactID() { return contactID; }
        public void setContactID(String contactID) { this.contactID = contactID; }
        
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }
    
    public static class ContactUpdateRequest {
        private String firstName;
        private String lastName;
        private String phone;
        private String address;
        
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }
}

/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: REST API Controller for Contact management operations
 */
package com.snhu.cs320.controller;

import ContactService.Contact;
import ContactService.ContactService;
import com.snhu.cs320.dto.ContactDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    
    private final ContactService contactService;
    
    public ContactController() {
        this.contactService = new ContactService();
    }
    
    /**
     * Create a new contact
     * POST /api/contacts
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createContact(@RequestBody ContactDTO contactDTO) {
        try {
            Contact contact = new Contact(
                contactDTO.getContactID(),
                contactDTO.getFirstName(),
                contactDTO.getLastName(),
                contactDTO.getPhone(),
                contactDTO.getAddress()
            );
            contactService.addContact(contact);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Contact created successfully");
            response.put("contactID", contactDTO.getContactID());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Update contact first name
     * PUT /api/contacts/{contactID}/firstName
     */
    @PutMapping("/{contactID}/firstName")
    public ResponseEntity<Map<String, String>> updateFirstName(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> request) {
        try {
            String firstName = request.get("firstName");
            contactService.updateFirstName(contactID, firstName);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "First name updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Update contact last name
     * PUT /api/contacts/{contactID}/lastName
     */
    @PutMapping("/{contactID}/lastName")
    public ResponseEntity<Map<String, String>> updateLastName(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> request) {
        try {
            String lastName = request.get("lastName");
            contactService.updateLastName(contactID, lastName);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Last name updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Update contact phone
     * PUT /api/contacts/{contactID}/phone
     */
    @PutMapping("/{contactID}/phone")
    public ResponseEntity<Map<String, String>> updatePhone(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> request) {
        try {
            String phone = request.get("phone");
            contactService.updatePhone(contactID, phone);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Phone updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Update contact address
     * PUT /api/contacts/{contactID}/address
     */
    @PutMapping("/{contactID}/address")
    public ResponseEntity<Map<String, String>> updateAddress(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> request) {
        try {
            String address = request.get("address");
            contactService.updateAddress(contactID, address);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Address updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Delete a contact
     * DELETE /api/contacts/{contactID}
     */
    @DeleteMapping("/{contactID}")
    public ResponseEntity<Map<String, String>> deleteContact(@PathVariable String contactID) {
        try {
            contactService.deleteContact(contactID);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Contact deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}

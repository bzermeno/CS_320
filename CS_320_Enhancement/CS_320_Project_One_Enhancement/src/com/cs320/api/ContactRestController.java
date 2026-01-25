/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026 
 *  Course ID: CS-320
 *  Description: REST API controller for Contact management operations.
 *  Provides HTTP endpoints for creating, updating, and deleting contacts.
 *  Accepts and returns JSON-formatted data.
 */
package com.cs320.api;

import ContactService.Contact;
import ContactService.ContactController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller exposing HTTP endpoints for Contact operations.
 * Maps HTTP requests to ContactController methods and returns JSON responses.
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactRestController {
    
    private final ContactController contactController;
    
    /**
     * Constructor initializes the controller layer.
     */
    public ContactRestController() {
        this.contactController = new ContactController();
    }
    
    /**
     * Creates a new contact.
     * 
     * POST /api/contacts
     * Request Body: { "contactID": "123", "firstName": "John", "lastName": "Doe", 
     *                 "phone": "1234567890", "address": "123 Main St" }
     * 
     * @param contactData Map containing contact information
     * @return ResponseEntity with success or error message
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createContact(@RequestBody Map<String, String> contactData) {
        try {
            // Validate request body
            if (contactData == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Error: Request body cannot be null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            String contactID = contactData.get("contactID");
            String firstName = contactData.get("firstName");
            String lastName = contactData.get("lastName");
            String phone = contactData.get("phone");
            String address = contactData.get("address");
            
            // Validate required fields
            if (contactID == null || firstName == null || lastName == null || phone == null || address == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Error: Missing required fields (contactID, firstName, lastName, phone, address)");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            String result = contactController.createContact(contactID, firstName, lastName, phone, address);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            response.put("contactID", contactID);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error creating contact: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Deletes a contact by ID.
     * 
     * DELETE /api/contacts/{contactID}
     * 
     * @param contactID The unique identifier of the contact
     * @return ResponseEntity with success or error message
     */
    @DeleteMapping("/{contactID}")
    public ResponseEntity<Map<String, String>> deleteContact(@PathVariable String contactID) {
        try {
            String result = contactController.deleteContact(contactID);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error deleting contact: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Updates a contact's first name.
     * 
     * PUT /api/contacts/{contactID}/firstName
     * Request Body: { "firstName": "Jane" }
     * 
     * @param contactID The unique identifier of the contact
     * @param requestData Map containing the new first name
     * @return ResponseEntity with success or error message
     */
    @PutMapping("/{contactID}/firstName")
    public ResponseEntity<Map<String, String>> updateFirstName(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> requestData) {
        try {
            String newFirstName = requestData.get("firstName");
            String result = contactController.updateContactFirstName(contactID, newFirstName);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating first name: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Updates a contact's last name.
     * 
     * PUT /api/contacts/{contactID}/lastName
     * Request Body: { "lastName": "Smith" }
     * 
     * @param contactID The unique identifier of the contact
     * @param requestData Map containing the new last name
     * @return ResponseEntity with success or error message
     */
    @PutMapping("/{contactID}/lastName")
    public ResponseEntity<Map<String, String>> updateLastName(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> requestData) {
        try {
            String newLastName = requestData.get("lastName");
            String result = contactController.updateContactLastName(contactID, newLastName);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating last name: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Updates a contact's phone number.
     * 
     * PUT /api/contacts/{contactID}/phone
     * Request Body: { "phone": "9876543210" }
     * 
     * @param contactID The unique identifier of the contact
     * @param requestData Map containing the new phone number
     * @return ResponseEntity with success or error message
     */
    @PutMapping("/{contactID}/phone")
    public ResponseEntity<Map<String, String>> updatePhone(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> requestData) {
        try {
            String newPhone = requestData.get("phone");
            String result = contactController.updateContactPhone(contactID, newPhone);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating phone: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Updates a contact's address.
     * 
     * PUT /api/contacts/{contactID}/address
     * Request Body: { "address": "456 Oak Ave" }
     * 
     * @param contactID The unique identifier of the contact
     * @param requestData Map containing the new address
     * @return ResponseEntity with success or error message
     */
    @PutMapping("/{contactID}/address")
    public ResponseEntity<Map<String, String>> updateAddress(
            @PathVariable String contactID, 
            @RequestBody Map<String, String> requestData) {
        try {
            String newAddress = requestData.get("address");
            String result = contactController.updateContactAddress(contactID, newAddress);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error updating address: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

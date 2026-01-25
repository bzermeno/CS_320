/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026 
 *  Course ID: CS-320
 *  Description: REST API controller for Appointment management operations.
 *  Provides HTTP endpoints for creating and deleting appointments.
 *  Accepts and returns JSON-formatted data.
 */
package com.cs320.api;

import AppointmentService.Appointment;
import AppointmentService.AppointmentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller exposing HTTP endpoints for Appointment operations.
 * Maps HTTP requests to AppointmentController methods and returns JSON responses.
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentRestController {
    
    private final AppointmentController appointmentController;
    
    /**
     * Constructor initializes the controller layer.
     */
    public AppointmentRestController() {
        this.appointmentController = new AppointmentController();
    }
    
    /**
     * Creates a new appointment.
     * 
     * POST /api/appointments
     * Request Body: { "appointmentID": "123", "date": "2026-12-31", "description": "Annual Checkup" }
     * 
     * @param appointmentData Map containing appointment information
     * @return ResponseEntity with success or error message
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createAppointment(@RequestBody Map<String, String> appointmentData) {
        try {
            // Validate request body
            if (appointmentData == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Error: Request body cannot be null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            String appointmentID = appointmentData.get("appointmentID");
            String dateStr = appointmentData.get("date");
            String description = appointmentData.get("description");
            
            // Validate required fields
            if (appointmentID == null || dateStr == null || description == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Error: Missing required fields (appointmentID, date, description)");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            // Parse date string to Date object (format: yyyy-MM-dd) using thread-safe LocalDate
            LocalDate localDate = LocalDate.parse(dateStr);
            Date appointmentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            String result = appointmentController.createAppointment(appointmentID, appointmentDate, description);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            response.put("message", result);
            response.put("appointmentID", appointmentID);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error creating appointment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Deletes an appointment by ID.
     * 
     * DELETE /api/appointments/{appointmentID}
     * 
     * @param appointmentID The unique identifier of the appointment
     * @return ResponseEntity with success or error message
     */
    @DeleteMapping("/{appointmentID}")
    public ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable String appointmentID) {
        try {
            String result = appointmentController.deleteAppointment(appointmentID);
            
            Map<String, String> response = new HashMap<>();
            if (result.startsWith("Error")) {
                response.put("message", result);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            response.put("message", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error deleting appointment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

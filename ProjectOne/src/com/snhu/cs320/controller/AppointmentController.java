/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: REST API Controller for Appointment management operations
 */
package com.snhu.cs320.controller;

import AppointmentService.Appointment;
import AppointmentService.AppointmentService;
import com.snhu.cs320.dto.AppointmentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    
    private final AppointmentService appointmentService;
    
    public AppointmentController() {
        this.appointmentService = new AppointmentService();
    }
    
    /**
     * Create a new appointment
     * POST /api/appointments
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            Appointment appointment = new Appointment(
                appointmentDTO.getAppointmentID(),
                appointmentDTO.getAppointmentDate(),
                appointmentDTO.getDescription()
            );
            appointmentService.addAppointment(appointment);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Appointment created successfully");
            response.put("appointmentID", appointmentDTO.getAppointmentID());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Get an appointment by ID
     * GET /api/appointments/{appointmentID}
     */
    @GetMapping("/{appointmentID}")
    public ResponseEntity<?> getAppointment(@PathVariable String appointmentID) {
        try {
            Appointment appointment = appointmentService.getAppointment(appointmentID);
            if (appointment == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Appointment not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            
            AppointmentDTO dto = new AppointmentDTO(
                appointment.getAppointmentID(),
                appointment.getAppointmentDate(),
                appointment.getDescription()
            );
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Delete an appointment
     * DELETE /api/appointments/{appointmentID}
     */
    @DeleteMapping("/{appointmentID}")
    public ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable String appointmentID) {
        try {
            appointmentService.deleteAppointment(appointmentID);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Appointment deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}

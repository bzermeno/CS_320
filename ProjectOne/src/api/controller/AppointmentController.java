/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: REST Controller for Appointment management with CRUD operations
 */
package api.controller;

import AppointmentService.Appointment;
import AppointmentService.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    
    private final AppointmentService appointmentService;
    
    public AppointmentController() {
        this.appointmentService = new AppointmentService();
    }
    
    /**
     * GET /api/appointments - Get all appointments
     */
    @GetMapping
    public ResponseEntity<Collection<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
    
    /**
     * GET /api/appointments/{id} - Get a specific appointment by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable String id) {
        try {
            Appointment appointment = appointmentService.getAppointment(id);
            if (appointment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(appointment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * POST /api/appointments - Create a new appointment
     */
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequest request) {
        try {
            Appointment appointment = new Appointment(
                request.getAppointmentID(),
                new Date(request.getAppointmentDate()),
                request.getDescription()
            );
            appointmentService.addAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * DELETE /api/appointments/{id} - Delete an appointment
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Inner class for request DTO
    public static class AppointmentRequest {
        private String appointmentID;
        private long appointmentDate; // Unix timestamp in milliseconds
        private String description;
        
        public String getAppointmentID() { return appointmentID; }
        public void setAppointmentID(String appointmentID) { this.appointmentID = appointmentID; }
        
        public long getAppointmentDate() { return appointmentDate; }
        public void setAppointmentDate(long appointmentDate) { this.appointmentDate = appointmentDate; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}

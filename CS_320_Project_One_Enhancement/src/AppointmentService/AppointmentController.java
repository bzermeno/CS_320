/*  Author Name: Beau Zermeno
 *  Date: January 24, 2026 
 *  Course ID: CS-320
 *  Description: AppointmentController handles incoming requests/user interactions for appointment management.
 *  This controller layer is responsible for input validation, request handling, and delegating
 *  business logic to the AppointmentService layer. It follows the Model-View-Controller (MVC) pattern.
 *  
 *  Purpose: Provides a clear entry point for appointment operations, handling user input/output and
 *  delegating to the service layer. This separation allows for different interfaces (CLI, GUI, REST API)
 *  to be easily implemented without changing business logic.
 */
package AppointmentService;

import java.util.Date;

/**
 * Controller layer for Appointment operations.
 * Handles user input validation and delegates to service layer.
 */
public class AppointmentController {
    private final AppointmentService appointmentService;
    
    /**
     * Constructor with dependency injection for service layer.
     * 
     * @param appointmentService The service layer instance for business logic
     */
    public AppointmentController(AppointmentService appointmentService) {
        if (appointmentService == null) {
            throw new IllegalArgumentException("AppointmentService must not be null.");
        }
        this.appointmentService = appointmentService;
    }
    
    /**
     * Default constructor that creates its own service instance.
     */
    public AppointmentController() {
        this.appointmentService = new AppointmentService();
    }
    
    /**
     * Handles request to create a new appointment.
     * Validates inputs and delegates to service layer.
     * 
     * @param appointmentID Unique identifier for the appointment
     * @param appointmentDate Date of the appointment
     * @param description Description of the appointment
     * @return Success message or error details
     */
    public String createAppointment(String appointmentID, Date appointmentDate, String description) {
        try {
            // Create appointment with validation happening in the Appointment constructor
            Appointment appointment = new Appointment(appointmentID, appointmentDate, description);
            
            // Delegate to service layer
            appointmentService.addAppointment(appointment);
            
            return "Appointment created successfully with ID: " + appointmentID;
        } catch (Exception e) {
            return "Error creating appointment: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to delete an appointment.
     * 
     * @param appointmentID The unique identifier of the appointment to delete
     * @return Success message or error details
     */
    public String deleteAppointment(String appointmentID) {
        try {
            // Input validation
            if (appointmentID == null || appointmentID.isEmpty()) {
                return "Error: Appointment ID cannot be null or empty";
            }
            
            // Delegate to service layer
            appointmentService.deleteAppointment(appointmentID);
            
            return "Appointment deleted successfully: " + appointmentID;
        } catch (Exception e) {
            return "Error deleting appointment: " + e.getMessage();
        }
    }
    
    /**
     * Handles request to retrieve an appointment.
     * 
     * @param appointmentID The unique identifier of the appointment
     * @return String representation of appointment details or error message
     */
    public String getAppointment(String appointmentID) {
        try {
            // Input validation
            if (appointmentID == null || appointmentID.isEmpty()) {
                return "Error: Appointment ID cannot be null or empty";
            }
            
            // Delegate to service layer
            Appointment appointment = appointmentService.getAppointment(appointmentID);
            
            if (appointment == null) {
                return "Appointment not found with ID: " + appointmentID;
            }
            
            return "Appointment Details - ID: " + appointment.getAppointmentID() + 
                   ", Date: " + appointment.getAppointmentDate() + 
                   ", Description: " + appointment.getDescription();
        } catch (Exception e) {
            return "Error retrieving appointment: " + e.getMessage();
        }
    }
}

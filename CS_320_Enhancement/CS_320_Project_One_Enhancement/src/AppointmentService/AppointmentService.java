/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: AppointmentService class contains business logic for managing Appointment objects.
 *  This service layer delegates data persistence operations to AppointmentDAO, following the
 *  layered architecture pattern. It focuses on business rules, validation, and coordination
 *  between the Controller and DAO layers.
 *  
 *  Purpose: Implements core business logic and rules for appointment management. This layer ensures
 *  consistent application logic and provides an abstraction between the Controller and DAO layers.
 *  Updated: January 24, 2026
 */
package AppointmentService;

/**
 * Service layer for Appointment business logic.
 * Coordinates between Controller and DAO layers, focusing on business rules.
 */
public class AppointmentService {
    // DAO instance for data access operations
    private final AppointmentDAO appointmentDAO;
    
    /**
     * Constructor with dependency injection for DAO layer.
     * 
     * @param appointmentDAO The data access object for appointment operations
     */
    public AppointmentService(AppointmentDAO appointmentDAO) {
        if (appointmentDAO == null) {
            throw new IllegalArgumentException("AppointmentDAO must not be null.");
        }
        this.appointmentDAO = appointmentDAO;
    }
    
    /**
     * Default constructor that creates its own DAO instance.
     * Useful for backward compatibility with existing tests.
     */
    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }

    /**
     * Adds a new appointment to the system.
     * Business logic: Validates appointment and ensures unique ID before saving.
     * 
     * @param appointment The Appointment object to add
     * @throws IllegalArgumentException if appointment ID already exists
     */
    public void addAppointment(Appointment appointment) {
        // Delegate to DAO layer for persistence
        appointmentDAO.save(appointment);
    }

    /**
     * Deletes an appointment from the system.
     * Business logic: Verifies appointment exists before deletion.
     * 
     * @param appointmentID The unique identifier of the appointment to delete
     * @throws IllegalArgumentException if appointmentID doesn't exist
     */
    public void deleteAppointment(String appointmentID) {
        // Delegate to DAO layer for deletion
        appointmentDAO.delete(appointmentID);
    }

    /**
     * Retrieves an appointment by its ID.
     * Business logic: Returns the appointment if found, null otherwise.
     * 
     * @param appointmentID The unique identifier of the appointment
     * @return The Appointment object, or null if not found
     */
    public Appointment getAppointment(String appointmentID) {
        return appointmentDAO.findById(appointmentID).orElse(null);
    }
}

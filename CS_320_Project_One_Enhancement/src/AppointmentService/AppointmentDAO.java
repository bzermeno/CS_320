/*  Author Name: Beau Zermeno
 *  Date: January 24, 2026 
 *  Course ID: CS-320
 *  Description: AppointmentDAO (Data Access Object) class handles all data persistence operations
 *  for Appointment objects. This layer encapsulates the data storage implementation (HashMap)
 *  and provides CRUD operations, following the separation of concerns principle.
 *  
 *  Purpose: Isolates data access logic from business logic, making the system more maintainable
 *  and allowing for easier changes to the underlying data storage mechanism without affecting
 *  other layers.
 */
package AppointmentService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Data Access Object for Appointment entities.
 * Handles all database/storage operations for appointments.
 */
public class AppointmentDAO {
    // HashMap to hold Appointment objects (in-memory storage)
    private final Map<String, Appointment> appointments = new HashMap<>();
    
    /**
     * Saves a new appointment to the data store.
     * 
     * @param appointment The Appointment object to save
     * @throws IllegalArgumentException if appointment is null or ID already exists
     */
    public void save(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment must not be null.");
        }
        if (appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment must have a unique ID.");
        }
        appointments.put(appointment.getAppointmentID(), appointment);
    }
    
    /**
     * Updates an existing appointment in the data store.
     * 
     * @param appointment The Appointment object with updated information
     * @throws IllegalArgumentException if appointment is null or doesn't exist
     */
    public void update(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment must not be null.");
        }
        if (!appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID not found");
        }
        appointments.put(appointment.getAppointmentID(), appointment);
    }
    
    /**
     * Deletes an appointment from the data store by ID.
     * 
     * @param appointmentID The unique identifier of the appointment to delete
     * @throws IllegalArgumentException if appointmentID doesn't exist
     */
    public void delete(String appointmentID) {
        if (!appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("Appointment ID not found");
        }
        appointments.remove(appointmentID);
    }
    
    /**
     * Finds an appointment by its unique ID.
     * 
     * @param appointmentID The unique identifier of the appointment
     * @return Optional containing the Appointment if found, empty Optional otherwise
     */
    public Optional<Appointment> findById(String appointmentID) {
        return Optional.ofNullable(appointments.get(appointmentID));
    }
    
    /**
     * Checks if an appointment with the given ID exists.
     * 
     * @param appointmentID The unique identifier to check
     * @return true if appointment exists, false otherwise
     */
    public boolean exists(String appointmentID) {
        return appointments.containsKey(appointmentID);
    }
}

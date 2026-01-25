/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: AppointmentRepository class handles data access operations for Appointment objects.
 *  This is the data access layer in the layered architecture.
 */
package AppointmentService;

import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentRepository {
    // HashMap to store Appointment objects
    private final HashMap<String, Appointment> appointments = new HashMap<>();
    
    /**
     * Adds an appointment to the repository
     * @param appointment The appointment to add
     */
    public void save(Appointment appointment) {
        appointments.put(appointment.getAppointmentID(), appointment);
    }
    
    /**
     * Finds an appointment by ID
     * @param appointmentID The ID of the appointment to find
     * @return The appointment if found, null otherwise
     */
    public Appointment findById(String appointmentID) {
        return appointments.get(appointmentID);
    }
    
    /**
     * Checks if an appointment exists by ID
     * @param appointmentID The ID to check
     * @return true if appointment exists, false otherwise
     */
    public boolean existsById(String appointmentID) {
        return appointments.containsKey(appointmentID);
    }
    
    /**
     * Deletes an appointment by ID
     * @param appointmentID The ID of the appointment to delete
     */
    public void deleteById(String appointmentID) {
        appointments.remove(appointmentID);
    }
    
    /**
     * Returns all appointments
     * @return Collection of all appointments
     */
    public Collection<Appointment> findAll() {
        return new ArrayList<>(appointments.values());
    }
}

/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: AppointmentService class allows user to add Appointment objects, delete
 *  an appointment identified by appointmentID. The appointmentID is immutable.
 *  This is the service layer in the layered architecture that uses AppointmentRepository for data access.
 */
package AppointmentService;

import java.util.Collection;

public class AppointmentService {
    // Repository for data access
    private final AppointmentRepository appointmentRepository;
    
    public AppointmentService() {
        this.appointmentRepository = new AppointmentRepository();
    }
    
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Add Appointment with unique appointmentID
    public void addAppointment(Appointment appointment) {
        if (appointmentRepository.existsById(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        appointmentRepository.save(appointment);
    }

    // Delete appointment if it is found
    public void deleteAppointment(String appointmentID) {
        if (!appointmentRepository.existsById(appointmentID)) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }
        appointmentRepository.deleteById(appointmentID);
    }

    // getter to retrieve an appointment by appointmentID
    public Appointment getAppointment(String appointmentID) {
        return appointmentRepository.findById(appointmentID);
    }
    
    public Collection<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}

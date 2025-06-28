/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: AppointmentService class allows user to add Task objects to the HashMap appointments, delete
 *  a task identified by appointmentID, and edit task fields, except for appointmentID, which is immutable.
 */
package AppointmentService;

import java.util.HashMap;

public class AppointmentService {
    // HashMap to store Appointment objects
	private final HashMap<String, Appointment> appointments = new HashMap<>();

    // Add Appointment with unique appointmentID to HashMap
    public void addAppointment(Appointment appointment) {
        if (appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        appointments.put(appointment.getAppointmentID(), appointment);
    }

    // Delete appointment if it is found
    public void deleteAppointment(String appointmentID) {
        if (!appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }
        appointments.remove(appointmentID);
    }

    // getter to retrieve an appointment by appointmentID
    public Appointment getAppointment(String appointmentID) {
        return appointments.get(appointmentID);
    }
}

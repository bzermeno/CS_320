/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: AppointmentServiceTest class tests that apppointments are successfully added to 
 *  the HashMap, appointments are deleted successfully, and that attempts to add invalid appointments 
 *  throw the proper exceptions.
 */
package AppointmentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

    private AppointmentService service;

    private Date getDateTwoWeeksOut() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 14); // 2 weeks from current date
        return calendar.getTime();
    }
    
    @BeforeEach
    public void setUp() {
        service = new AppointmentService();
    }

    @Test
    public void testAddAppointmentSuccess() {
        Appointment appointment = new Appointment("001", getDateTwoWeeksOut(), "Dentist appointment");
        service.addAppointment(appointment);
        assertEquals(appointment, service.getAppointment("001"));
    }

    @Test
    public void testAddDuplicateAppointmentThrowsException() {
        Appointment appointment1 = new Appointment("001", getDateTwoWeeksOut(), "Dentist appointment");
        Appointment appointment2 = new Appointment("001", getDateTwoWeeksOut(), "Eye checkup");
        service.addAppointment(appointment1);
        // appointmentID already exists
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(appointment2);
        });
    }

    @Test
    public void testDeleteAppointmentSuccessfully() {
        Appointment appointment = new Appointment("002", getDateTwoWeeksOut(), "Meeting with client");
        service.addAppointment(appointment);
        service.deleteAppointment("002");
        assertNull(service.getAppointment("002"));
    }

    @Test
    public void testDeleteNonExistentAppointmentThrowsException() {
        // appointmentID does not exist
    	assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment("999");
        });
    }

    @Test
    public void testGetNonExistentAppointmentReturnsNull() {
        assertNull(service.getAppointment("123"));
    }
}

